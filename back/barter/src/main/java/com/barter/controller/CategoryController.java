package com.barter.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.domain.Product;
import com.barter.model.Category;
import com.barter.model.User;
import com.barter.service.CategoryService;
import com.barter.tools.CurrentUser;
import com.barter.tools.LoginRequired;
import com.barter.tools.Result;
import com.barter.tools.ResultGenerator;
import com.barter.tools.TableData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 类别
	 * 
	 * @Title: get
	 * @Description: TODO
	 * @param pageNum
	 * @param size
	 * @param product
	 * @param map
	 * @return Result
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Result get(Integer pageNum, Integer size, Product product, @RequestParam Map<String, Object> map) {
		Page<Category> page = PageHelper.startPage(pageNum == null ? 1 : pageNum, size == null ? 40 : size);
		List<Category> list = categoryService.findList(map);
		return ResultGenerator.genSuccessResult(new TableData<Category>(page.getTotal(), list));
	}

	/**
	 * 添加
	 * 
	 * @Title: inset
	 * @Description: TODO
	 * @param category
	 * @return Result
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@LoginRequired
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result inset(@RequestParam(required = false) Map<String, Object> map, @CurrentUser User currentUser,
			@RequestParam(required = false) MultipartFile image) throws IllegalAccessException, InvocationTargetException {
		if (!currentUser.getUserType().equals("1")) {
			return ResultGenerator.genFailResult("您无权访问");
		}
		Category category = new Category();
		BeanUtils.populate(category, map);
		return ResultGenerator.genSuccessResult(categoryService.add(category, image));
	}

	/**
	 * 修改
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param map
	 * @return Result
	 */
	@LoginRequired
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Result update(@PathVariable String id, @RequestParam(required = false) Map<String, Object> map,
			@CurrentUser User currentUser) {
		if (!currentUser.getUserType().equals("1")) {
			return ResultGenerator.genFailResult("您无权访问");
		}
		List<Category> cates = categoryService.findList(map);
		if (cates.size() != 0 && !cates.get(0).getId().equals(id)) {
			return ResultGenerator.genFailResult("该类别名称已存在");
		}
		map.put("id", id);
		return ResultGenerator.genSuccessResult(categoryService.update(map));
	}

	/**
	 * 根据id查询
	 * 
	 * @Title: selectById
	 * @Description: TODO
	 * @param id
	 * @return Result
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result selectById(@PathVariable(value = "id") String id) {
		return ResultGenerator.genSuccessResult(categoryService.findById(id));
	}

	/**
	 * 删除
	 * 
	 * @Title: selectById
	 * @Description: TODO
	 * @param id
	 * @return Result
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable(value = "id") String id) {
		return ResultGenerator.genSuccessResult(categoryService.delete(id));
	}
}