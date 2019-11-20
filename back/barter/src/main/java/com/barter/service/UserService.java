package com.barter.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.barter.mapper.UserMapper;
import com.barter.model.User;
import com.barter.tools.MD5;
import com.barter.tools.SendEmailUtil;
import com.barter.tools.ServiceException;
import com.barter.tools.TokenUtil;
import com.barter.tools.UUIDUtils;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	// 读取配置文件中的参数
	@Value("${spring.mail.username}")
	private String form;

	@Value("${local_url}")
	private String domain;

	@Autowired
	private JavaMailSender javaMailSender;

	public void addUser(User user) {
		user.setId(UUIDUtils.get16UUID());
		user.setCreateTime(new Date());
		user.setPassword(MD5.md5(user.getPassword()));

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

	/**
	 * 用户注册
	 * 
	 * @Title: addUser
	 * @Description: TODO
	 * @param user
	 * @param file
	 * @return String
	 * @throws MessagingException
	 */
	public User add(User user) throws MessagingException {
		String password = user.getPassword();
		user.setPassword(MD5.md5(password));
		user.setCreateTime(new Date());
		user.setEmail(user.getLoginName());

		String activeCode = UUIDUtils.getActiveCode();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, 2); // 存入过期时间
		if (!user.getLoginName().matches("^\\w+@(\\w+\\.)+\\w+$")) {
			throw new ServiceException("邮箱不合法");
		}
		User newuser = userMapper.selectByLoginName(user.getLoginName());
		if (newuser != null) {
			// 如果存在并且已经激活
			if (newuser.getState().equals("0")) {
				throw new ServiceException("邮箱已被注册");
			}
			// 如账号存在并且没激活则重新发送邮件，并且更新该用户数据
			if (newuser.getState().equals("4")) {
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("activeCode", activeCode);
				userMap.put("activeDate", now.getTime());
				userMap.put("id", newuser.getId());
				userMap.put("password", MD5.md5(user.getPassword()));
				// 发送邮件
				SendEmailUtil.send("点击激活邮箱：<a href='" + domain + "/active.html?id=" + newuser.getId()
						+ "&activeCode=" + activeCode + "'>" + domain + "/active.html?id=" + newuser.getId()
						+ "&activeCode=" + activeCode + "</a>该链接48小时内有效", user.getLoginName(), "激活邮箱", javaMailSender,
						form);
				userMapper.updateDymic(userMap);
				return user;
			}
		}
		user.setId(UUIDUtils.get16UUID());
		user.setActiveCode(activeCode);
		user.setActiveDate(now.getTime()); // 存入过期时间,两天后过期
		user.setState("4"); // 未激活状态
		user.setUserType("0"); // 非管理员
		// 发送邮件
		SendEmailUtil.send(
				"点击激活邮箱：<a href='" + domain + "/active.html?id=" + user.getId() + "&activeCode=" + activeCode + "'>"
						+ domain + "/active.html?id=" + user.getId() + "&activeCode=" + activeCode + "</a>该链接48小时内有效",
				user.getLoginName(), "激活邮箱", javaMailSender, form);
		userMapper.insert(user);
		return user;
	}

	public void updateByUser(User user) {
		
	}
}
