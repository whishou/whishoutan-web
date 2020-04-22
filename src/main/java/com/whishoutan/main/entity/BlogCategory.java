package com.whishoutan.main.entity;

import java.util.ArrayList;
import java.util.List;

public class BlogCategory {
    private Integer id;
    private String category;
    private Integer categoryCounts;

    private List<Blog> blogs;

    public BlogCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryCounts() {
        return categoryCounts;
    }

    public void setCategoryCounts(Integer categoryCounts) {
        this.categoryCounts = categoryCounts;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "BlogCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", categoryCounts=" + categoryCounts +
                '}';
    }
}
