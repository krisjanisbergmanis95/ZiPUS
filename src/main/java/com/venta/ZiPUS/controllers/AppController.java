package com.venta.ZiPUS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/hello") // endpoint for localhost:8080
    public String showHelloPage() {
        System.out.println("showHelloPage");
        return "hellopage";// this should show html "Hello my dude"
    }
}
