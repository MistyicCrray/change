package com.barter.model.dyna;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class OrderItemDynaProvider {
	public String selectProvider(Map<String, Object> map) {
		return new SQL() {
			{
				SELECT("*");
				FROM("t_order_item");
				if (map != null) {
					if (map.get("orderId") != null) {
						WHERE("order_id=#{orderId}");
					}
					if (map.get("userid") != null) {
						WHERE("userid=#{userid}");
					}
					if (map.get("orderItemId") != null) {
						WHERE("order_item_id=#{orderItemId}");
					}
					if (map.get("sellid") != null) {
						WHERE("sellid=#{sellid}");
					}
					if (map.get("status") != null) {
						WHERE("status=#{status}");
					}
					if (map.get("create_time") != null) {
						WHERE("create_time=#{create_time}");
					}
				}
			}
		}.toString();
	}

	public String updateProvider(Map<String, Object> map) {
		return new SQL() {
			{
				UPDATE("t_order_item");
				if (map != null) {
					if (map.get("userId") != null) {
						SET("user_id=#{user_id}");
					}
					if (map.get("sellId") != null) {
						SET("sell_id=#{sellId}");
					}
					if (map.get("status") != null) {
						SET("status=#{status}");
					}
					if (map.get("updateTime") != null) {
						SET("update_time=#{updateTime}");
					}
					if (map.get("confirmTime") != null) {
						SET("confirm_time=#{confirmTime}");
					}
					if (map.get("status") != null) {
						SET("status=#{status}");
					}
				}
				WHERE("order_item_id=#{orderItemId}");
			}
		}.toString();
	}

	public String selectBySql(Map<String, Object> map) {
		return new SQL() {
			{
				SELECT("o.order_id, o.order_item_id, o.create_time, o.quantity, o.sell_id"
						+ ", o.payment, o.status, u.user_name,p.id, p.name" + ", p.price, p.img");
				FROM("t_order_item o");
				LEFT_OUTER_JOIN("t_user u ON u.id=o.user_id");
				LEFT_OUTER_JOIN("t_product p ON p.id=o.product_id");
				if (map != null) {
					if (map.get("orderId") != null) {
						WHERE("o.order_id=#{orderId}");
					}
					if (map.get("orderItemId") != null) {
						WHERE("o.order_item_id=#{orderItemId}");
					}
					if (map.get("userId") != null) {
						WHERE("o.user_id=#{userId}");
					}
					if (map.get("sellId") != null) {
						WHERE("o.sell_id=#{sellId} or o.user_id=#{userId}");
					}
					if (map.get("status") != null) {
						WHERE("o.status=#{status}");
					}
					if (map.get("create_time") != null) {
						WHERE("o.create_time=#{create_time}");
					}
				}
			}
		}.toString();
	}
	
	public String select(Map<String, Object> map) {
		return new SQL() {
			{
				SELECT("o.order_id, o.order_item_id, o.create_time, o.quantity, o.sell_id"
						+ ", o.payment, o.status, u.user_name,p.id, p.name" + ", p.price, p.img");
				FROM("t_order_item o");
				LEFT_OUTER_JOIN("t_user u ON u.id=o.user_id");
				LEFT_OUTER_JOIN("t_product p ON p.id=o.product_id");
				if (map != null) {
					if (map.get("orderId") != null) {
						WHERE("o.order_id=#{orderId}");
					}
					if (map.get("orderItemId") != null) {
						WHERE("o.order_item_id=#{orderItemId}");
					}
					if (map.get("sellId") != null) {
						WHERE("o.sell_id=#{sellId} or o.user_id=#{userId}");
					}
					if (map.get("status") != null) {
						WHERE("o.status=#{status}");
					}
					if (map.get("create_time") != null) {
						WHERE("o.create_time=#{create_time}");
					}
				}
			}
		}.toString();
	}
}
