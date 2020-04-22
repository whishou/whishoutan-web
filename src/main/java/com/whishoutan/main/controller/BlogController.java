package com.whishoutan.main.controller;

import com.whishoutan.main.entity.Blog;
import com.whishoutan.main.entity.BlogCategory;
import com.whishoutan.main.entity.Page;
import com.whishoutan.main.service.BlogCategoryService;
import com.whishoutan.main.service.BlogService;
import com.whishoutan.main.util.TimeConvert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
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

        TimeConvert.timeConvert(queryBlog,1);

        BlogCategory blogCategory = blogCategoryService.getBlogCategory(queryBlog.getCategoryID());

        //System.out.println(blogCategory.toString());

        model.addAttribute("queryBlog",queryBlog);
        model.addAttribute("str",queryBlog.getUpTime());
        model.addAttribute("blogCategory",blogCategory);

        return "admin/myblogs";
    }

    @RequestMapping("/myblogList")
    public String blogList(Model model)
    {
        List<Blog> blogList= blogService.getBlogList();
        //System.out.println(blogList);

        TimeConvert.timeConvert(blogList,1);

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

        TimeConvert.timeConvert(blogPages,1);


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


    /*** 以下是关于category的操作  ***/

    @RequestMapping("/categories")
    public String categories(Model model)
    {
        List<BlogCategory> categoryList = blogCategoryService.getAllCategory();

        for (int i = 0; i < categoryList.size(); i++) {
            categoryList.get(i).setCategoryCounts(blogService.getCategoryCounts(categoryList.get(i).getId()));
        }
        model.addAttribute("categoryList",categoryList);
        return "admin/myblogCategories";
    }

    @RequestMapping("/updateCategories")
    public String updateCategories(Model model,String category,Integer id)
    {
        if (category != null)
        {
            blogCategoryService.newCategory(category);
        }
        else if (id != null)
        {
            blogCategoryService.deleteCategory(id);
        }

        return "redirect:/loginIndex/categories";
    }
}
