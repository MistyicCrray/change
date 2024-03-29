package com.barter.model;

import java.util.Date;

public class News {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.id
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.title
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.content
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.sub
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String sub;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.createdate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.updatedate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private Date updatedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.createby
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String createby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.updateby
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String updateby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_news.istop
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    private String istop;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.id
     *
     * @return the value of t_news.id
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.id
     *
     * @param id the value for t_news.id
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.title
     *
     * @return the value of t_news.title
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.title
     *
     * @param title the value for t_news.title
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.content
     *
     * @return the value of t_news.content
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.content
     *
     * @param content the value for t_news.content
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.sub
     *
     * @return the value of t_news.sub
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getSub() {
        return sub;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.sub
     *
     * @param sub the value for t_news.sub
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setSub(String sub) {
        this.sub = sub == null ? null : sub.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.createdate
     *
     * @return the value of t_news.createdate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.createdate
     *
     * @param createdate the value for t_news.createdate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.updatedate
     *
     * @return the value of t_news.updatedate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.updatedate
     *
     * @param updatedate the value for t_news.updatedate
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.createby
     *
     * @return the value of t_news.createby
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.createby
     *
     * @param createby the value for t_news.createby
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.updateby
     *
     * @return the value of t_news.updateby
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getUpdateby() {
        return updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.updateby
     *
     * @param updateby the value for t_news.updateby
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_news.istop
     *
     * @return the value of t_news.istop
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public String getIstop() {
        return istop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_news.istop
     *
     * @param istop the value for t_news.istop
     *
     * @mbggenerated Thu Mar 14 09:08:27 CST 2019
     */
    public void setIstop(String istop) {
        this.istop = istop == null ? null : istop.trim();
    }
}