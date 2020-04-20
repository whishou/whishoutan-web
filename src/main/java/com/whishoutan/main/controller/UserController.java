package com.whishoutan.main.controller;

import com.whishoutan.main.entity.User;
import com.whishoutan.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginIndex")
public class UserController {
    //@Autowired
    //private UserService userService;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping
    public String loginIndex(){
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession httpSession, RedirectAttributes redirectAttributes)
    {
        boolean success = userService.findUserByUserAndPassword(user);
        //System.out.println(success);

        if (success)
        {
            httpSession.setAttribute("user",user);
            return "admin/success";
        }
        else
        {
            redirectAttributes.addFlashAttribute("message","USERNAME OR PASSWORD IS NOT CORRECT");
            return "redirect:/loginIndex";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession)
    {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }

}
