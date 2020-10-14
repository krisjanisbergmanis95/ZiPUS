package com.venta.zipus.controllers;

import com.venta.zipus.controllers.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
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
    public String showHomePage() {
        logger.info("home page");
        return "home";
    }
}
