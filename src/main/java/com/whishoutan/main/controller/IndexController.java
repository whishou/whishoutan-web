package com.whishoutan.main.controller;


import com.whishoutan.main.entity.Blog;
import com.whishoutan.main.entity.Page;
import com.whishoutan.main.service.BlogCategoryService;
import com.whishoutan.main.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Controller
public class IndexController {

    private final BlogService blogService;

    private final BlogCategoryService blogCategoryService;

    public IndexController(BlogService blogService, BlogCategoryService blogCategoryService)
    {
        this.blogService = blogService;
        this.blogCategoryService = blogCategoryService;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/blog")
    public String blog(Model model, int page)
    {
        Page p = new Page();
        p.setCurrentPage(page);
        List<Blog> blogPages= blogService.getBlogListByPages((p.getCurrentPage()-1) * p.getPageSize(),p.getPageSize());
        p.setTotalCounts(blogService.getTotalCounts());
        p.setTotalPages(p.getTotalPages());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        for (int i = 0; i < blogPages.size(); i++) {
            blogPages.get(i).setCrTime(simpleDateFormat.format(blogPages.get(i).getCreateTime()));
            blogPages.get(i).setUpTime(simpleDateFormat.format(blogPages.get(i).getUpdateTime()));
        }
        //System.out.println(blogPages.toString());

        model.addAttribute("queryBlogList",blogPages);

        model.addAttribute("totalPages",p.getTotalPages());
        model.addAttribute("currentPage",p.getCurrentPage());
        model.addAttribute("nextPage",p.getNextPage());
        model.addAttribute("prePage",p.getPrePage());

        return "blog";
    }

    @RequestMapping("/article")
    public String article(Model model,Blog blog){
        Blog myBlog = blogService.getBlog(blog);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        myBlog.setCrTime(simpleDateFormat.format(myBlog.getCreateTime()));
        myBlog.setUpTime(simpleDateFormat.format(myBlog.getUpdateTime()));
        //System.out.println(myBlog.toString());

        model.addAttribute("myBlog",myBlog);


        return "article";
    }
}
