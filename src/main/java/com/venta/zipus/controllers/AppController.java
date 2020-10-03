package com.venta.zipus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
    @Autowired

    @GetMapping("/login") // endpoint for localhost:8080
    public String showHelloPage() {
        return "login-page";// this should show html "Hello my dude"
    }

    @GetMapping("all/**")
    public String allDirectories(HttpServletRequest request) {
        return request.getRequestURI()
                .split(request.getContextPath() + "/all/")[1];
    }
}
