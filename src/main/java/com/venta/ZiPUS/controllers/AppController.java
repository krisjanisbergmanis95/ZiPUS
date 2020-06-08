package com.venta.ZiPUS.controllers;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModelBook;
import com.venta.ZiPUS.repositories.IPublicationTypeBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @Autowired
    IPublicationTypeBookRepo bookTypeRepo;

    @GetMapping("") // endpoint for localhost:8080
    public String showHelloPage() {
        System.out.println("1111111111");
        PublicationTypeModelBook p1 = new PublicationTypeModelBook("Books");
        bookTypeRepo.save(p1);
        System.out.println("2222222222");
        return "hellopage";// this should show html "Hello my dude"
    }
}
