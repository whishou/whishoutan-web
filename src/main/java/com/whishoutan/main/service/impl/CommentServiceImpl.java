package com.whishoutan.main.service.impl;

import com.whishoutan.main.dao.CommentMapper;
import com.whishoutan.main.entity.Comment;
import com.whishoutan.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> getCommentByBlogID(Integer blogID) {
        return commentMapper.findCommentByBlogID(blogID);
    }

    @Override
    public List<Comment> getReply(Integer blogID, Integer id) {
        List<Comment> list =  commentMapper.findReply(blogID, id);
        //查询出parentNickname,便于在前端显示回复谁
        for (Comment comment : list) {
            comment.setParentNickName(getParentNicknameById(id));
            comment.setParentID(id);
        }
        return handleReply(blogID,list);
    }



    /**
     * 循环迭代评论
     * @param blogID
     * @param list
     * @return
     */
    private List<Comment> handleReply(Integer blogID,List<Comment> list)
    {
        List<Comment> comments = list;
        recursively(blogID,comments);

        return comments;
    }

    private void recursively(Integer blogID,List<Comment> list)
    {
        for (int i = 0; i < list.size(); i++) {
            List<Comment> comments = commentMapper.findReply(blogID,list.get(i).getId());

            for (int j = 0; j < comments.size(); j++) {
                comments.get(j).setParentNickName(list.get(i).getNickName());
                comments.get(j).setParentID(list.get(i).getId());
                //不使用list.addAll，以更改评论顺序，增强可读性
                list.add(i+j+1,comments.get(j));
            }
//            for (int j = 0; j < comments.size(); j++) {
//                list.add(comments.get(j));
//            }
            //list.addAll(comments);

            if (comments.size()>0)
            {
                recursively(blogID,comments);
            }
        }

    }

    /*****************************************************/

    @Override
    public String getParentNicknameById(Integer id) {
        return commentMapper.findParentNicknameById(id);
    }

    @Override
    public void newComment(Comment comment) {
        if (comment.getCommentID() ==-1)
        {
            comment.setCommentID(null);
        }
        commentMapper.newComment(comment);
    }
}
