package com.barter.model.dyna;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class UserDynaProvider {
	public String selectProvider(Map<String, Object> map) {
		return new SQL() {
			{
				SELECT("*");
				FROM("t_user");
				if (map != null) {
					if (map.get("id") != null) {
						WHERE("id=#{id}");
					}
					if (map.get("username") != null) {
						WHERE("username like '%" + map.get("username") + "%'");
					}
					if (map.get("password") != null) {
						WHERE("password=#{password}");
					}
					if (map.get("gender") != null) {
						WHERE("gender=#{gender}");
					}
					if (map.get("img") != null) {
						WHERE("img=#{img}");
					}
					if (map.get("state") != null) {
						WHERE("state=#{state}");
					}
					if (map.get("address") != null) {
						WHERE("address=#{address}");
					}
					if (map.get("tel") != null) {
						WHERE("tel=#{tel}");
					}
					if (map.get("createdate") != null) {
						WHERE("createdate=#{createdate}");
					}
					if (map.get("loginName") != null) {
						WHERE("login_name=#{loginName}");
					}
					if (map.get("email") != null) {
						WHERE("email=#{email}");
					}
					if (map.get("userType") != null) {
						WHERE("user_type=#{userType}");
					}
				}
			}
		}.toString();
	}

	public String updateProvider(Map<String, Object> map) {
		return new SQL() {
			{
				UPDATE("t_user");
				if (map.get("userName") != null) {
					SET("user_name=#{userName}");
				}
				if (map.get("password") != null) {
					SET("password=#{password}");
				}
				if (map.get("gender") != null) {
					SET("gender=#{gender}");
				}
				if (map.get("img") != null) {
					SET("img=#{img}");
				}
				if (map.get("state") != null) {
					SET("state=#{state}");
				}
				if (map.get("phone") != null) {
					SET("phone=#{phone}");
				}
				if (map.get("loginName") != null) {
					SET("login_name=#{loginName}");
				}
				if (map.get("email") != null) {
					SET("email=#{email}");
				}
				if (map.get("adreess") != null) {
					SET("adreess=#{adreess}");
				}
				if (map.get("lastLoginTime") != null) {
					SET("last_Login_Time=#{lastLoginTime}");
				}
				if (map.get("province") != null) {
					SET("province=#{province}");
				}
				if (map.get("city") != null) {
					SET("city=#{city}");
				}
				if (map.get("area") != null) {
					SET("area=#{area}");
				}
				WHERE("id=#{id}");
			}
		}.toString();
	}
}
