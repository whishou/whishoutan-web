package com.whishoutan.main.service;

import com.whishoutan.main.entity.Blog;

import java.util.List;

public interface BlogService {
    Blog getBlog(Blog blog);

    List<Blog> getBlogList();

    Integer getTotalCounts();

    List<Blog> getBlogListByPages(Integer start, Integer pageSize);

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Integer id);
}
