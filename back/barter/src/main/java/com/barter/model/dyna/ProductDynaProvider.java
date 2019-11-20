package com.barter.model.dyna;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class ProductDynaProvider {
	public String updateProvider(Map<String, Object> param) {
		return new SQL() {
			{
				UPDATE("t_product");
				if (param.get("name") != null) {
					SET("name=#{name}");
				}
				if (param.get("detail") != null) {
					SET("detail=#{detail}");
				}
				if (param.get("img") != null) {
					SET("img=#{img}");
				}
				if (param.get("category") != null) {
					SET("category=#{category}");
				}
				if (param.get("price") != null) {
					SET("price=#{price}");
				}
				if (param.get("color") != null) {
					SET("color=#{color}");
				}
				if (param.get("userId") != null) {
					SET("user_id=#{userId}");
				}
				if (param.get("quality") != null) {
					SET("quality=#{quality}");
				}
				if (param.get("hits") != null) {
					SET("hits=#{hits}");
				}
				if (param.get("updateBy") != null) {
					SET("update_by=#{updateBy}");
				}
				if (param.get("status") != null) {
					SET("status=#{status}");
				}
				if (param.get("changeWhat") != null) {
					SET("change_what=#{changeWhat}");
				}
				if (param.get("oldPrice") != null) {
					SET("old_price=#{oldPrice}");
				}
				WHERE("id=#{id}");
			}
		}.toString();
	}

	// 类别查询
	public String selectProvider(Map<String, Object> param) {
		return new SQL() {
			{
				SELECT("*");
				FROM("t_product");
				if (param.get("id") != null) {
					WHERE("id=#{id}");
				}
				if (param.get("name") != null) {
					WHERE("name=#{name}");
				}
				if (param.get("category") != null) {
					// 父类别和子类别同时查询
					WHERE("category=ANY(SELECT ID FROM T_CATEGORY WHERE PARENTID=#{category} or id=#{category}) OR category=#{category}");
				}
				if (param.get("price") != null) {
					WHERE("price=#{price}");
				}
				if (param.get("color") != null) {
					WHERE("color=#{color}");
				}
				if (param.get("userId") != null) {
					WHERE("user_id=#{userId}");
				}
				if (param.get("img") != null) {
					WHERE("img=#{img}");
				}
				if (param.get("hits") != null) {
					WHERE("hits=#{hits}");
				}
				if (param.get("quality") != null) {
					WHERE("quality=#{quality}");
				}
			}
		}.toString();
	}

	public String searchList(String key) {
		return new SQL() {
			{
				SELECT("*");
				FROM("t_product");
				WHERE("proid LIKE '%" + key + "%' OR" + " name LIKE '%" + key
						+ "%' OR category=ANY(SELECT ID FROM T_CATEGORY WHERE name LIKE '%" + key + "%') OR detail LIKE '%"
						+ key + "%'");
			}
		}.toString();
	}

}
