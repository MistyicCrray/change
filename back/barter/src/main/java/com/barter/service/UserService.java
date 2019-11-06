package com.barter.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barter.mapper.UserMapper;
import com.barter.model.User;
import com.barter.tools.MD5;
import com.barter.tools.TokenUtil;
import com.barter.tools.UUIDUtils;


@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public void addUser(User user) {
		user.setId(UUIDUtils.get16UUID());
		user.setCreateTime(new Date());
		user.setPassword(MD5.md5(user.getPassword()));
		user.setState(0);

		userMapper.insert(user);
	}

	public User findById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 登录
	 * 
	 * @Title: login
	 * @Description: TODO
	 * @param map
	 * @return Map<String,Object>
	 */
	public Map<String, Object> login(User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		user.setPassword(MD5.md5(user.getPassword()));
		User users = userMapper.findByLoginNameAndPassword(user);
		if (users == null) {
			return null;
		}
		String token = TokenUtil.createJwtToken(users.getId());// 存入token
		resultMap.put("userInfo", users); // 存入user信息
		resultMap.put("accessToken", token);
		return resultMap;
	}

	public List<User> findUserAll() {
		return userMapper.selectAll();
	}

	public List<User> findDymic(Map<String, Object> map) {
		return userMapper.findByDymic(map);
	}

	public User findByLoginName(String loginName) {
		Map<String, Object> map = new HashMap<>();
		map.put("loginName", loginName);
		List<User> users = userMapper.findByDymic(map);
		if (users.size() == 0) {
			return null;
		}
		return users.get(0);
	}

	public void deleteUser(String id) {
		userMapper.deleteByPrimaryKey(id);
	}

	public void updateDymic(Map<String, Object> map) {
		userMapper.updateDymic(map);
	}

	public void updatePwd(String password, User user) {
		user.setPassword(MD5.md5(password));
		userMapper.updateByPrimaryKey(user);
	}

}
