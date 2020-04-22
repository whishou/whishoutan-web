package com.whishoutan.main.service.impl;

import com.whishoutan.main.dao.BlogCategoryMapper;
import com.whishoutan.main.entity.BlogCategory;
import com.whishoutan.main.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public BlogCategory getBlogCategory(Integer id) {
        return blogCategoryMapper.blogCategory(id);
    }

    @Override
    public List<BlogCategory> getAllCategory() {
        return blogCategoryMapper.allCategory();
    }

    @Override
    public void newCategory(String string) {
        blogCategoryMapper.newCategory(string);
    }

    @Override
    public void deleteCategory(Integer id) {
        blogCategoryMapper.deleteCategory(id);
    }
}
