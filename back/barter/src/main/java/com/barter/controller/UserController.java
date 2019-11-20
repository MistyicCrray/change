package com.barter.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barter.model.User;
import com.barter.service.UserService;
import com.barter.tools.CurrentUser;
import com.barter.tools.LoginRequired;
import com.barter.tools.MD5;
import com.barter.tools.Result;
import com.barter.tools.ResultGenerator;
import com.barter.tools.TableData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 管理员添加用户
	 * 
	 * @param map
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	@LoginRequired
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result addUser(@RequestBody Map<String, Object> map, @CurrentUser User cruse)
			throws IllegalAccessException, InvocationTargetException, ParseException {
		if (!cruse.getUserType().equals(0)) {
			return ResultGenerator.genFailResult("你没有操作权限");
		}
		User user = new User();
		BeanUtils.populate(user, map);
		if (userService.findByLoginName(user.getLoginName()) != null) {
			return ResultGenerator.genFailResult("添加失败，该账号已存在");
		}
		userService.addUser(user);
		return ResultGenerator.genSuccessResult("添加成功");
	}

	/**
	 * 查询所有用户信息
	 * 
	 * @throws ParseException
	 */
	@LoginRequired
	@RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
	public Result addUser(@RequestParam(required = false) Map<String, Object> map, Integer pageNum, Integer size,
			@CurrentUser User cruse) {
		if (!cruse.getUserType().equals(0)) {
			return ResultGenerator.genFailResult("你没有操作权限");
		}
		Page<User> page = PageHelper.startPage(pageNum == null ? 1 : pageNum, size == null ? 5 : size);
		List<User> findUserAll = userService.findDymic(map);
		return ResultGenerator.genSuccessResult(new TableData<User>(page.getTotal(), findUserAll));
	}

	/**
	 * 登录
	 * 
	 * @param map
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestBody Map<String, Object> map) throws IllegalAccessException, InvocationTargetException {
		User user = new User();
		BeanUtils.populate(user, map);
		Map<String, Object> login = userService.login(user);
		if (login == null) {
			return ResultGenerator.genFailResult("用户名或密码错误");
		}
		User curUser = (User) login.get("userInfo");
		if (curUser.getState().equals(1)) {
			return ResultGenerator.genFailResult("账号已被冻结，请联系管理员");
		}
		return ResultGenerator.genSuccessResult(login);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param cruse
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.POST)
	public Result deleteUser(@PathVariable String id, @CurrentUser User cruse) {
		if (!cruse.getUserType().equals(0)) {
			return ResultGenerator.genFailResult("你没有操作权限");
		}
		if (userService.findById(id).getUserType().equals(0)) {
			return ResultGenerator.genFailResult("管理员用户不能删除");
		}
		userService.deleteUser(id);
		return ResultGenerator.genSuccessResult("删除成功");
	}

	/**
	 * 修改
	 * 
	 * @param cruse
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
	public Result update(@RequestBody Map<String, Object> map, @PathVariable(value = "id") String id,
			@CurrentUser User cruse) {
		map.put("id", id);
		userService.updateDymic(map);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	/**
	 * 修改密码
	 * 
	 * @param cruse
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/updatePwd/{password}/{oldP}", method = RequestMethod.POST)
	public Result updatePwd(@PathVariable(value = "password") String password,
			@PathVariable(value = "oldP") String oldP, @CurrentUser User cruse) {
		String md5 = MD5.md5(oldP);
		if (!md5.equals(cruse.getPassword())) {
			return ResultGenerator.genFailResult("旧密码不正确");
		}
		userService.updatePwd(password, cruse);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	/**
	 * 用户注册
	 * 
	 * @Title: insertUser
	 * @Description: TODO
	 * @param user
	 * @param file
	 * @return Result
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result insertUser(@RequestBody User user) throws MessagingException {
		User add = userService.add(user);
		return ResultGenerator.genSuccessResult(add);
	}
	
	/**
	 * 邮箱激活
	 * 
	 * @param id
	 * @param activeCode
	 * @return
	 */
	@RequestMapping(value = "/active/{id}/{activeCode}", method = RequestMethod.GET)
	public Result activeEmail(@PathVariable String id, @PathVariable String activeCode) {
		User user = userService.findById(id);
		if (user == null) {
			return ResultGenerator.genFailResult("用户还未注册，请前往注册页面");
		}
		if (user.getState().equals("0") && StringUtil.isEmpty(user.getActiveCode())) {
			return ResultGenerator.genFailResult("该邮箱已激活");
		}
		if (!activeCode.equals(user.getActiveCode())) {
			return ResultGenerator.genFailResult("激活码错误");
		}
		// 链接过期则删除用户信息
		if (new Date().getTime() >= user.getActiveDate().getTime()) {
			userService.deleteUser(id);
			return ResultGenerator.genFailResult("该链接已经过期,请重新注册");
		}
		// 激活成功时清空这两个字段,防止重复激活
		user.setActiveDate(null);
		user.setActiveCode(null);

		user.setState("0"); // 设置为正常可用状态
		userService.updateByUser(user);
		return ResultGenerator.genSuccessResult("success");
	}
}
