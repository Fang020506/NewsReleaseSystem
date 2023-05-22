package com.news.controller;

import com.news.entity.User;
import com.news.service.UserService;
import com.news.service.impl.UserValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sancean
 * @since 2023-05-18
 */
@RestController
@RequestMapping("/news/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param name
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User user = userService.login(name, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:index.jsp";
        } else {
            return "login.jsp";
        }
    }

    /**
     * 用户注册
     * @param user
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/register")
    public String register(@ModelAttribute("user") User user,
                           BindingResult bindingResult) throws Exception {
        new UserValidator().validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register.jsp";
        } else {
            userService.register(user);
            return "redirect:login.jsp";
        }
    }
}
