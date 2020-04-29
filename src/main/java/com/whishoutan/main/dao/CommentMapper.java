package com.whishoutan.main.dao;

import com.whishoutan.main.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment where blogID = #{blogID} and commentID is null")
    List<Comment> findCommentByBlogID(Integer blogID);

    @Select("select * from comment where blogID = #{blogID} and commentID = #{id}")
    List<Comment> findReply(Integer blogID,Integer id);

    @Select("select nickname from comment where id = #{id}")
    String findParentNicknameById(Integer id);

    @Insert("insert into comment (nickName,email,text,avatar,createTime,blogID,commentID) values (#{nickName},#{email},#{text},'qaq',#{createTime},#{blogID},#{commentID});")
    void newComment(Comment comment);
}
