package com.whishoutan.main.service;

import com.whishoutan.main.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogID(Integer blogID);

    List<Comment> getReply(Integer blogID,Integer id);

    String getParentNicknameById(Integer id);

    void newComment(Comment comment);
}
