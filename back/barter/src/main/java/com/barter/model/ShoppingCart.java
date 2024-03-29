package com.barter.model;

import java.util.Date;

public class ShoppingCart {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shoppingcart.cartid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String cartid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shoppingcart.userid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shoppingcart.quantity
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private Integer quantity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shoppingcart.total
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private Float total;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shoppingcart.productid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String productid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shoppingcart.createdate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_shoppingcart.ischeck
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String ischeck;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shoppingcart.cartid
     *
     * @return the value of t_shoppingcart.cartid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getCartid() {
        return cartid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shoppingcart.cartid
     *
     * @param cartid the value for t_shoppingcart.cartid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setCartid(String cartid) {
        this.cartid = cartid == null ? null : cartid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shoppingcart.userid
     *
     * @return the value of t_shoppingcart.userid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shoppingcart.userid
     *
     * @param userid the value for t_shoppingcart.userid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shoppingcart.quantity
     *
     * @return the value of t_shoppingcart.quantity
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shoppingcart.quantity
     *
     * @param quantity the value for t_shoppingcart.quantity
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shoppingcart.total
     *
     * @return the value of t_shoppingcart.total
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public Float getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shoppingcart.total
     *
     * @param total the value for t_shoppingcart.total
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setTotal(Float total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shoppingcart.productid
     *
     * @return the value of t_shoppingcart.productid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getProductid() {
        return productid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shoppingcart.productid
     *
     * @param productid the value for t_shoppingcart.productid
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shoppingcart.createdate
     *
     * @return the value of t_shoppingcart.createdate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shoppingcart.createdate
     *
     * @param createdate the value for t_shoppingcart.createdate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_shoppingcart.ischeck
     *
     * @return the value of t_shoppingcart.ischeck
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getIscheck() {
        return ischeck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_shoppingcart.ischeck
     *
     * @param ischeck the value for t_shoppingcart.ischeck
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setIscheck(String ischeck) {
        this.ischeck = ischeck == null ? null : ischeck.trim();
    }
}