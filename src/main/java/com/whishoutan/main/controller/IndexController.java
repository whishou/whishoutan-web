package com.whishoutan.main.controller;


import com.whishoutan.main.entity.Blog;
import com.whishoutan.main.entity.BlogCategory;
import com.whishoutan.main.entity.Comment;
import com.whishoutan.main.entity.Page;
import com.whishoutan.main.service.BlogCategoryService;
import com.whishoutan.main.service.BlogService;
import com.whishoutan.main.service.CommentService;
import com.whishoutan.main.util.MarkdownToHTML;
import com.whishoutan.main.util.TimeConvert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    private final BlogService blogService;

    private final BlogCategoryService blogCategoryService;

    private final CommentService commentService;

    public IndexController(BlogService blogService, BlogCategoryService blogCategoryService, CommentService commentService)
    {
        this.blogService = blogService;
        this.blogCategoryService = blogCategoryService;
        this.commentService = commentService;
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
    public String article(Model model,Blog blog)
    {
        Blog myBlog = blogService.getBlog(blog);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        myBlog.setCrTime(simpleDateFormat.format(myBlog.getCreateTime()));
//        myBlog.setUpTime(simpleDateFormat.format(myBlog.getUpdateTime()));

        TimeConvert.timeConvert(myBlog,2);

        String string = MarkdownToHTML.markdownToHTML(myBlog.getText());
        //System.out.println(string);

        List<Comment> commentList = commentService.getCommentByBlogID(blog.getId());
        TimeConvert.timeConvert(commentList);

        //根据commentID，查询reply列表
//        for (int i = 0; i < commentList.size(); i++) {
//            commentList.get(i).setReplyComments(commentService.getReply(blog.getId(),commentList.get(i).getId()));
//            TimeConvert.timeConvert(commentList.get(i).getReplyComments());
//        }
        for (Comment comment : commentList) {
            comment.setReplyComments(commentService.getReply(blog.getId(), comment.getId()));

            TimeConvert.timeConvert(comment.getReplyComments());
        }

        //System.out.println(myBlog.toString());
        //System.out.println(commentList.toString());

        model.addAttribute("myBlog",myBlog);
        model.addAttribute("text",string);

        model.addAttribute("commentList",commentList);

        return "article";
    }

    @RequestMapping("/comment")
    public String comments(Comment comment, RedirectAttributes attributes)
    {
        Date date = new Date();
        comment.setCreateTime(date);
        //System.out.println(comment.toString());
        commentService.newComment(comment);

        attributes.addAttribute("id",comment.getBlogID());
        return "redirect:/article";
    }
}
