package com.whishoutan.main.controller;

import com.whishoutan.main.entity.Blog;
import com.whishoutan.main.entity.BlogCategory;
import com.whishoutan.main.entity.Page;
import com.whishoutan.main.service.BlogCategoryService;
import com.whishoutan.main.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping("/loginIndex")
public class BlogController {

    private final BlogService blogService;

    private final BlogCategoryService blogCategoryService;

    public BlogController(BlogService blogService, BlogCategoryService blogCategoryService)
    {
        this.blogService = blogService;
        this.blogCategoryService = blogCategoryService;
    }



    @RequestMapping("/myblogs")
    public String blogs(Model model,Blog blog)
    {
        Blog queryBlog =blogService.getBlog(blog);
        //System.out.println(queryBlog.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String str = simpleDateFormat.format(queryBlog.getUpdateTime());

        BlogCategory blogCategory = blogCategoryService.getBlogCategory(queryBlog.getCategoryID());

        //System.out.println(blogCategory.toString());

        model.addAttribute("queryBlog",queryBlog);
        model.addAttribute("str",str);
        model.addAttribute("blogCategory",blogCategory);

        return "admin/myblogs";
    }

    @RequestMapping("/myblogList")
    public String blogList(Model model)
    {
        List<Blog> blogList= blogService.getBlogList();
        //System.out.println(blogList);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        for (int i = 0; i < blogList.size(); i++) {
            blogList.get(i).setCrTime(simpleDateFormat.format(blogList.get(i).getCreateTime()));
            blogList.get(i).setUpTime(simpleDateFormat.format(blogList.get(i).getUpdateTime()));
        }

        model.addAttribute("queryBlogList",blogList);

        return "admin/myblogList";
    }

    @RequestMapping("/myblogPages")
    public String blogPages(Model model,int page)
    {
        Page p = new Page();
        p.setCurrentPage(page);
        List<Blog> blogPages= blogService.getBlogListByPages((p.getCurrentPage()-1) * p.getPageSize(),p.getPageSize());
        p.setTotalCounts(blogService.getTotalCounts());
        p.setTotalPages(p.getTotalPages());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        for (int i = 0; i < blogPages.size(); i++) {
            blogPages.get(i).setCrTime(simpleDateFormat.format(blogPages.get(i).getCreateTime()));
            blogPages.get(i).setUpTime(simpleDateFormat.format(blogPages.get(i).getUpdateTime()));
        }


        model.addAttribute("queryBlogList",blogPages);

        model.addAttribute("totalPages",p.getTotalPages());
        model.addAttribute("currentPage",p.getCurrentPage());
        model.addAttribute("nextPage",p.getNextPage());
        model.addAttribute("prePage",p.getPrePage());



        return "admin/myblogPages";
    }


    @RequestMapping("/newBlogs")
    public String newBlogs(Model model,Blog blog)
    {

        List<BlogCategory> categoryList = blogCategoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        //System.out.println(blog.toString());

        if (blog.getId() != null)
        {
            Blog queryBlog = blogService.getBlog(blog);
            model.addAttribute("id",queryBlog.getId());
            model.addAttribute("title",queryBlog.getTitle());
            model.addAttribute("categoryID",queryBlog.getCategoryID());
            model.addAttribute("text",queryBlog.getText());


        }
        return "admin/newblogs";

    }



    @RequestMapping("/submitBlogs")
    public String submitBlogs(Blog blog)
    {
        Date date = new Date();
        //System.out.println(blog.getId());
        if (blog.getId() !=null)
        {
            blog.setUpdateTime(date);
            blogService.updateBlog(blog);
        }
        else
        {
            blog.setCreateTime(date);
            blog.setUpdateTime(date);
            blogService.saveBlog(blog);
        }

        return "admin/success";
    }

    @RequestMapping("/deleteBlogs")
    public String deleteBlogs(Integer id)
    {
        blogService.deleteBlog(id);

        return "admin/success";
    }
}
