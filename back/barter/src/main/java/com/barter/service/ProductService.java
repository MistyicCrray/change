package com.barter.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.barter.mapper.CategoryMapper;
import com.barter.mapper.OrderItemMapper;
import com.barter.mapper.OrderMapper;
import com.barter.mapper.ProductMapper;
import com.barter.mapper.UserMapper;
import com.barter.model.Category;
import com.barter.model.Order;
import com.barter.model.OrderItem;
import com.barter.model.Product;
import com.barter.model.User;
import com.barter.tools.FileUtil;
import com.barter.tools.UUIDUtils;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderMapper orderMapper;

	public Integer add(Product product, MultipartFile file) {
		product.setId(UUIDUtils.get16UUID());
		product.setCreateDate(new Date());
		product.setUpdateDate(new Date());
		product.setHits(0);
		if (file != null) {
			product.setImg((String) FileUtil.uploadImage(file).get("filePath"));
		}
		return productMapper.insert(product);
	}

	public Integer delete(String id) {
		return productMapper.deleteByPrimaryKey(id);
	}

	public Integer update(Map<String, Object> map, MultipartFile file) {
		if (file != null) {
			map.put("img", (String) FileUtil.uploadImage(file).get("filePath"));
		} else {
			if (Integer.parseInt(map.get("quality").toString()) > 0) {
				map.put("status", 0);// 正常状态
			}
		}
		return productMapper.update(map);
	}

	public List<Product> findList(Map<String, Object> map) {
		List<Product> products = productMapper.findList(map);
		for (Product product : products) {
			User user = userMapper.selectByPrimaryKey(product.getUserId());
			Category category = categoryMapper.selectByPrimaryKey(product.getCategory());
			product.setCategory2(category);
			product.setUser(user);
		}
		return products;
	}

	public Product findById(String id) {
		Product product = productMapper.selectByPrimaryKey(id);
		User user = userMapper.selectByPrimaryKey(product.getUserId());
		Category category = categoryMapper.selectByPrimaryKey(product.getCategory());
		product.setCategory2(category);
		product.setUser(user);
		return product;
	}

	/**
	 * 增加浏览量
	 */
	public Integer getHits(String id) {
		Product product = productMapper.selectByPrimaryKey(id);
		if (product != null) {
			Integer i = product.getHits();
			if (i == null || i == 0) {
				i = 1;
			} else {
				i++;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("proid", id);
			map.put("hits", i);
			return productMapper.update(map);
		}
		return 0;
	}

	public List<Product> searchList(String key) {
		List<Product> products = productMapper.search(key);
		for (Product product : products) {
			User user = userMapper.selectByPrimaryKey(product.getUserId());
			Category category = categoryMapper.selectByPrimaryKey(product.getCategory());
			product.setCategory2(category);
			product.setUser(user);
		}
		return products;
	}

	public int delImg(String id) {
		Product selectByPrimaryKey = productMapper.selectByPrimaryKey(id);
		FileUtil.delFile(selectByPrimaryKey.getImg());
		return productMapper.deleImg(id);
	}

	public static void main(String[] args) {
		FileUtil.delFile("3nWTn41bM2t3AT84.jpg");
	}

	public void changeProduct(Map<String, Object> map) {
		String curProId = map.get("curProId").toString();
		String changeProId = map.get("changeProId").toString();
		Product curProduct = productMapper.selectByPrimaryKey(curProId);
		Order order = new Order();
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderItemId(UUIDUtils.get16UUID());

	}

}
