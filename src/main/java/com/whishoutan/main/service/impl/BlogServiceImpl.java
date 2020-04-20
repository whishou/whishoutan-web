package com.whishoutan.main.service.impl;

import com.whishoutan.main.dao.BlogMapper;
import com.whishoutan.main.entity.Blog;
import com.whishoutan.main.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlog(Blog blog) {
        return blogMapper.findBlogById(blog);
    }

    @Override
    public List<Blog> getBlogList() {
        return blogMapper.findBlogByList();
    }

    @Override
    public Integer getTotalCounts() {
        return blogMapper.getTotalCounts();
    }

    @Override
    public List<Blog> getBlogListByPages(Integer start, Integer pageSize) {
        return blogMapper.findBlogByPages(start, pageSize);
    }


    @Override
    public void saveBlog(Blog blog) {
        blogMapper.saveBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Integer id) {
        blogMapper.deleteBlog(id);
    }
}
