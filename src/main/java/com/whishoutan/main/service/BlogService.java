package com.whishoutan.main.service;

import com.whishoutan.main.entity.Blog;

import java.util.List;

public interface BlogService {
    Blog getBlog(Blog blog);

    List<Blog> getBlogList();

    List<Blog> getBlogByCategory(Integer categoryID);

    Integer getTotalCounts();

    //查询某一分类下文章的数量
    Integer getCategoryCounts(Integer categoryID);

    List<Blog> getBlogListByPages(Integer start, Integer pageSize);

    //查询状态为发布的文章
    List<Blog> getPublishedBlogListByPage(Integer start, Integer pageSize);

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Integer id);
}
