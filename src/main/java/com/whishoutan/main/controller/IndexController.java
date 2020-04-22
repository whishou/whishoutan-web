package com.whishoutan.main.controller;


import com.whishoutan.main.entity.Blog;
import com.whishoutan.main.entity.BlogCategory;
import com.whishoutan.main.entity.Page;
import com.whishoutan.main.service.BlogCategoryService;
import com.whishoutan.main.service.BlogService;
import com.whishoutan.main.util.MarkdownToHTML;
import com.whishoutan.main.util.TimeConvert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        List<Blog> blogPages= blogService.getPublishedBlogListByPage((p.getCurrentPage()-1) * p.getPageSize(),p.getPageSize());
        p.setTotalCounts(blogService.getTotalCounts());
        p.setTotalPages(p.getTotalPages());

        List<BlogCategory> categoryList = blogCategoryService.getAllCategory();

        //根据categoryID查询某分类下文章数量
//        for (int i = 0; i < categoryList.size(); i++) {
//            categoryList.get(i).setCategoryCounts(blogService.getCategoryCounts(categoryList.get(i).getId()));
//        }
        for (BlogCategory blogCategory : categoryList) {
            blogCategory.setCategoryCounts(blogService.getCategoryCounts(blogCategory.getId()));
        }
        //System.out.println(categoryList.toString());
        //System.out.println(blogPages.toString());
        TimeConvert.timeConvert(blogPages,2);

        List<Blog> finalBlogPages = MarkdownToHTML.markdownToHTML(blogPages);

        model.addAttribute("queryBlogList",finalBlogPages);
        model.addAttribute("categoryList",categoryList);

        model.addAttribute("totalPages",p.getTotalPages());
        model.addAttribute("currentPage",p.getCurrentPage());
        model.addAttribute("nextPage",p.getNextPage());
        model.addAttribute("prePage",p.getPrePage());

        return "blog";
    }

    @RequestMapping("/blogByCategory")
    public String blogByCategory(Model model,Integer categoryID)
    {
        List<Blog> blogList = blogService.getBlogByCategory(categoryID);

        List<BlogCategory> categoryList = blogCategoryService.getAllCategory();
        //根据categoryID查询某分类下文章数量
        for (BlogCategory blogCategory : categoryList) {
            blogCategory.setCategoryCounts(blogService.getCategoryCounts(blogCategory.getId()));
        }
        TimeConvert.timeConvert(blogList,2);

        List<Blog> finalBlogPages = MarkdownToHTML.markdownToHTML(blogList);

        model.addAttribute("queryBlogList",finalBlogPages);
        model.addAttribute("categoryList",categoryList);
        //当前分类
        model.addAttribute("currentCategory",blogCategoryService.getBlogCategory(categoryID).getCategory());

        return "blogByCategory";
    }

    @RequestMapping("/article")
    public String article(Model model,Blog blog){
        Blog myBlog = blogService.getBlog(blog);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        myBlog.setCrTime(simpleDateFormat.format(myBlog.getCreateTime()));
//        myBlog.setUpTime(simpleDateFormat.format(myBlog.getUpdateTime()));

        TimeConvert.timeConvert(myBlog,2);

        String string = MarkdownToHTML.markdownToHTML(myBlog.getText());
        //System.out.println(string);

        //System.out.println(myBlog.toString());
        model.addAttribute("myBlog",myBlog);
        model.addAttribute("text",string);

        return "article";
    }
}
