package com.whishoutan.main.service;

import com.whishoutan.main.entity.BlogCategory;

import java.util.List;

public interface BlogCategoryService {
    BlogCategory getBlogCategory(Integer id);

    List<BlogCategory> getAllCategory();

    void newCategory(String string);

    void deleteCategory(Integer id);
}
