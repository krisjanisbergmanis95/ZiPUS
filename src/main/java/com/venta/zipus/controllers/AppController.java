package com.venta.zipus.controllers;

import com.venta.zipus.config.WebSecurityConfig;
import com.venta.zipus.controllers.user.UserController;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IUserAuthorityService;
import com.venta.zipus.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.venta.zipus.helpers.UserHelper.getCurrentUsername;

@Controller
public class AppController {
    @Autowired
    IUserService userService;
    @Autowired
    IUserAuthorityService userAuthorityService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/login") // endpoint for localhost:8080
    public String showLogInPage() {
        if (isAuthenticated()) {
            logger.info("I am logged in. redirect to home");
            return "redirect:/home/";
        }
        logger.info("I am not logged in");
        return "login-page";
    }

    @PostMapping("/login") // endpoint for localhost:8080
    public String login() {
        logger.info("POST login");
        return "redirect:/home/";
    }



    @GetMapping("/home") // endpoint for localhost:8080
    public String showHomePage(Model model) {
        logger.info("home page");
        logger.info(getCurrentUsername());
//        logger.info(tempUser.getName());
        User user = userService.getUserByUsername(getCurrentUsername());
        model.addAttribute("user", user);
        model.addAttribute("isAuthorityAuthor",
                user.isAuthority(userAuthorityService.getUserAuthorityByTitle(WebSecurityConfig.AUTHOR)));
//        logger.info(id);
        return "home";
    }
}
