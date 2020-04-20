package com.whishoutan.main.dao;

import com.whishoutan.main.entity.BlogCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogCategoryMapper {
    @Select("select * from blogCategory where id = #{id}")
    BlogCategory blogCategory(Integer id);

    @Select("select * from blogCategory")
    List<BlogCategory> allCategory();

}
