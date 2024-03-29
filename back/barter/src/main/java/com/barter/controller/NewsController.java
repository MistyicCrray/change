package com.barter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barter.model.News;
import com.barter.model.User;
import com.barter.service.NewsService;
import com.barter.tools.CurrentUser;
import com.barter.tools.LoginRequired;
import com.barter.tools.Result;
import com.barter.tools.ResultGenerator;
import com.barter.tools.TableData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	/**
	 * 查询
	 * 
	 * @Title: getAll
	 * @Description: TODO
	 * @param map
	 * @return Result
	 * @author hlx
	 * @date 2018年11月19日下午5:18:54
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Result getAll(Integer pageNum, Integer size, @RequestParam(required = false) Map<String, Object> map) {
		Page<News> page = PageHelper.startPage(pageNum == null ? 1 : pageNum, size == null ? 5 : size,"istop");
		List<News> list = newsService.findList(map);
		return ResultGenerator.genSuccessResult(new TableData<News>(page.getTotal(), list));
	}

	/**
	 * 添加
	 * 
	 * @Title: add
	 * @Description: TODO
	 * @param news
	 * @return Result
	 * @author hlx
	 * @date 2018年11月19日下午5:20:11
	 */
	@LoginRequired
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result add(@RequestBody News news, @CurrentUser User currentUser) {
		if (!currentUser.getUserType().equals("1")) {
			return ResultGenerator.genFailResult("您无权访问");
		}
		news.setCreateby(currentUser.getUserName());
		return ResultGenerator.genSuccessResult(newsService.add(news));
	}

	/**
	 * 删除
	 * @param id
	 * @param currentUser
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id, @CurrentUser User currentUser) {
		if (!currentUser.getUserType().equals("1")) {
			return ResultGenerator.genFailResult("您无权访问");
		}
		return ResultGenerator.genSuccessResult(newsService.delete(id));
	}

	/**
	 * 修改
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param map
	 * @return Result
	 * @author hlx
	 * @date 2018年11月19日下午5:21:54
	 */
	@LoginRequired
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Result update(@RequestBody News news, @PathVariable String id,
			@CurrentUser User currentUser) {
		if (!currentUser.getUserType().equals("1")) {
			return ResultGenerator.genFailResult("您无权访问");
		}
		news.setUpdateby(currentUser.getUserName());
		return ResultGenerator.genSuccessResult(newsService.update(news));
	}

	/**
	 * 详情
	 * 
	 * @Title: findById
	 * @Description: TODO
	 * @param id
	 * @return Result
	 * @author hlx
	 * @date 2018年11月19日下午5:27:36
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result findById(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(newsService.findById(id));
	}

}
