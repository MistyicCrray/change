package com.barter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.barter.mapper.CategoryMapper;
import com.barter.mapper.ProductMapper;
import com.barter.model.Category;
import com.barter.model.Product;
import com.barter.tools.FileUtil;
import com.barter.tools.ServiceException;
import com.github.pagehelper.StringUtil;

@Service
public class CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ProductMapper productMapper;

	public int add(Category category, MultipartFile image) {
		int id = 1001;
		List<Category> list = categoryMapper.selectAll();
		// 当这个类型为父类型时
		if (StringUtil.isEmpty(category.getParentid())) {
			Map<String, Object> cateMap = new HashMap<>();
			cateMap.put("parentid", "0");
			List<Category> sonList = categoryMapper.findList(cateMap);
			if (list.size() == 0) {
				category.setId("1001");
				category.setParentid("0");
			} else {
				category.setId(id + sonList.size() + "");
				category.setParentid("0");
			}
			// 当这个类型为子类型时
		} else {
			Map<String, Object> cateMap = new HashMap<>();
			cateMap.put("parentid", category.getParentid());
			List<Category> sonList = categoryMapper.findList(cateMap);
			if (sonList.size() < 10) {
				category.setId(category.getParentid() + "0" + sonList.size());
			} else {
				category.setId(category.getParentid() + sonList.size() + "");
			}
			if (image != null && !image.getName().equals("")) {
				category.setImg((String) FileUtil.uploadImage(image).get("filePath"));
			}
		}
		for (Category cate : list) {
			if (category.getParentid().equals(cate.getParentid()) && category.getName().equals(cate.getName())) {
				throw new ServiceException("该类型已存在");
			}
		}
		return categoryMapper.insert(category);
	}

	public int delete(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("cateid", id);
		List<Product> proList = productMapper.findList(map);
		if (proList.size() > 0) {
			for (Product product : proList) {
				if(product.getCategory().equals(id));
				throw new ServiceException("该类型已存在使用商品，暂不可删除");
			}
		}
		return categoryMapper.deleteByPrimaryKey(id);
	}

	public Integer update(Map<String, Object> map) {
		return categoryMapper.update(map);
	}

	public List<Category> findList(Map<String, Object> map) {
		return categoryMapper.findList(map);
	}

	public Category findById(String id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

}
