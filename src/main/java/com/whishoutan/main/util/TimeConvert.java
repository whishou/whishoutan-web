package com.whishoutan.main.util;

import com.whishoutan.main.entity.Blog;
import com.whishoutan.main.entity.Comment;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class TimeConvert {
    /**
     *
     *integer==1 output "yyyy-MM-dd HH:mm:ss"
     *integer!=1 output "yyyy-MM-dd"
     *
     */

    public static void timeConvert(Blog blog, Integer integer)
    {
        String dateFormat;
        if (integer==1)
        {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        }
        else
        {
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        blog.setCrTime(simpleDateFormat.format(blog.getCreateTime()));
        blog.setUpTime(simpleDateFormat.format(blog.getUpdateTime()));

    }

    public static void timeConvert(List<Blog> list,Integer integer)
    {
        String dateFormat;
        if (integer==1)
        {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        }
        else
        {
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        for (Blog blog : list)
        {
            blog.setCrTime(simpleDateFormat.format(blog.getCreateTime()));
            blog.setUpTime(simpleDateFormat.format(blog.getUpdateTime()));
        }
    }

    public static void timeConvert(List<Comment> list)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        for (Comment comment : list)
        {
            comment.setCrTime(simpleDateFormat.format(comment.getCreateTime()));
        }
    }
}
