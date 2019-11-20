package com.barter.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

public class Product {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.id
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String id;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.name
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String name;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.category
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String category;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.price
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private Double price;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.detail
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String detail;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.create_date
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private Date createDate;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.update_date
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private Date updateDate;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.create_by
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String createBy;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.update_by
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String updateBy;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.img
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String img;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.user_id
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String userId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.hits
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private Integer hits;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.faver
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private Integer faver;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.status
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String status;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.quality
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private Integer quality;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.change_what
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private String changeWhat;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_product.old_price
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	private Double oldPrice;

	@Transient
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Transient
	private Category category2;

	public Category getCategory2() {
		return category2;
	}

	public void setCategory2(Category category2) {
		this.category2 = category2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.id
	 *
	 * @return the value of t_product.id
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.id
	 *
	 * @param id
	 *            the value for t_product.id
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.name
	 *
	 * @return the value of t_product.name
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.name
	 *
	 * @param name
	 *            the value for t_product.name
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.category
	 *
	 * @return the value of t_product.category
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.category
	 *
	 * @param category
	 *            the value for t_product.category
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.price
	 *
	 * @return the value of t_product.price
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.price
	 *
	 * @param price
	 *            the value for t_product.price
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.detail
	 *
	 * @return the value of t_product.detail
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.detail
	 *
	 * @param detail
	 *            the value for t_product.detail
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.create_date
	 *
	 * @return the value of t_product.create_date
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.create_date
	 *
	 * @param createDate
	 *            the value for t_product.create_date
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.update_date
	 *
	 * @return the value of t_product.update_date
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.update_date
	 *
	 * @param updateDate
	 *            the value for t_product.update_date
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.create_by
	 *
	 * @return the value of t_product.create_by
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.create_by
	 *
	 * @param createBy
	 *            the value for t_product.create_by
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.update_by
	 *
	 * @return the value of t_product.update_by
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.update_by
	 *
	 * @param updateBy
	 *            the value for t_product.update_by
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.img
	 *
	 * @return the value of t_product.img
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getImg() {
		return img;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.img
	 *
	 * @param img
	 *            the value for t_product.img
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.user_id
	 *
	 * @return the value of t_product.user_id
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.user_id
	 *
	 * @param userId
	 *            the value for t_product.user_id
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.hits
	 *
	 * @return the value of t_product.hits
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public Integer getHits() {
		return hits;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.hits
	 *
	 * @param hits
	 *            the value for t_product.hits
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.faver
	 *
	 * @return the value of t_product.faver
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public Integer getFaver() {
		return faver;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.faver
	 *
	 * @param faver
	 *            the value for t_product.faver
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setFaver(Integer faver) {
		this.faver = faver;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.status
	 *
	 * @return the value of t_product.status
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.status
	 *
	 * @param status
	 *            the value for t_product.status
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.quality
	 *
	 * @return the value of t_product.quality
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public Integer getQuality() {
		return quality;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.quality
	 *
	 * @param quality
	 *            the value for t_product.quality
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.change_what
	 *
	 * @return the value of t_product.change_what
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public String getChangeWhat() {
		return changeWhat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.change_what
	 *
	 * @param changeWhat
	 *            the value for t_product.change_what
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setChangeWhat(String changeWhat) {
		this.changeWhat = changeWhat == null ? null : changeWhat.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_product.old_price
	 *
	 * @return the value of t_product.old_price
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public Double getOldPrice() {
		return oldPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_product.old_price
	 *
	 * @param oldPrice
	 *            the value for t_product.old_price
	 *
	 * @mbg.generated Wed Nov 13 22:38:05 CST 2019
	 */
	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
}