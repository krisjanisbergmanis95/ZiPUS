package com.venta.zipus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @Autowired

    @GetMapping("") // endpoint for localhost:8080
    public String showHelloPage() {
        return "hellopage";// this should show html "Hello my dude"
    }
}
