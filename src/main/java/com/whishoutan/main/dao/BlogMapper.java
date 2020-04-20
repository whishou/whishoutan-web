package com.whishoutan.main.dao;

import com.whishoutan.main.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("select * from blog left join blogCategory on blog.categoryID = blogCategory.id where blog.id = #{id}")
    Blog findBlogById(Blog blog);

    // @Select("select blog.id, title, text, if_publish, blog.createTime, blog.updateTime,categoryID, category, userID  from blog left join blogCategory on blog.categoryID = blogCategory.id left join user on user.id=blog.userID order by blog.id")
    @Select("select * from blog left join blogCategory on blog.categoryID = blogCategory.id order by blog.id")
    List<Blog> findBlogByList();

    @Select("select count(*) from blog")
    Integer getTotalCounts();

    @Select("select * from blog left join blogCategory on blog.categoryID = blogCategory.id order by blog.createTime desc limit #{start},#{pageSize}")
    List<Blog> findBlogByPages(Integer start, Integer pageSize);

    @Insert("insert into blog (title,text,if_publish,createTime,updateTime,categoryID) values (#{title},#{text},#{if_publish},#{createTime},#{updateTime},#{categoryID})")
    void saveBlog(Blog blog);

    @Update("update blog set title =#{title} ,text = #{text}, if_publish = #{if_publish},updateTime =#{updateTime}, categoryID= #{categoryID} where id = #{id}")
    void updateBlog(Blog blog);

    @Delete("delete from blog where id = #{id}")
    void deleteBlog(Integer id);


}
