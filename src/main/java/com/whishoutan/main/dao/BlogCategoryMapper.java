package com.whishoutan.main.dao;

import com.whishoutan.main.entity.BlogCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogCategoryMapper {
    @Select("select * from blogCategory where id = #{id}")
    BlogCategory blogCategory(Integer id);

    @Select("select * from blogCategory")
    List<BlogCategory> allCategory();

    @Insert("insert into blogCategory (category) values (#{string})")
    void newCategory(String string);

    @Delete("delete from blogCategory where id= #{id}")
    void deleteCategory(Integer id);
}
