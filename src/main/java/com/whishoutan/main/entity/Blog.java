package com.whishoutan.main.entity;

import java.util.Date;
import java.util.List;

public class Blog {

    private Integer id;
    private String title;
    private String text;
    private boolean if_publish;
    private Date createTime;
    private Date updateTime;
    private Integer categoryID;

    private String category;
    private String crTime;
    private String upTime;


    private User user;
    private List<Comment> comments;


    public Blog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIf_publish() {
        return if_publish;
    }

    public void setIf_publish(boolean if_publish) {
        this.if_publish = if_publish;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCrTime() {
        return crTime;
    }

    public void setCrTime(String crTime) {
        this.crTime = crTime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", if_publish=" + if_publish +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", categoryID=" + categoryID +
                ", category='" + category + '\'' +
                ", crTime='" + crTime + '\'' +
                ", upTime='" + upTime + '\'' +
                '}';
    }
}
