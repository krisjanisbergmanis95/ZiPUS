package com.venta.ZiPUS.controllers.publication;

import com.venta.ZiPUS.models.publications.Publication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test/publications") // endpoint for localhost:8080
    public String showHelloPage() {
//        Publication p1 = new Publication("Mācību grāmata", "english");
        try {
//            p1.setPublicationGroupByType();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        System.out.println(p1.toString());
        return "hellopage";// this should show html "Hello my dude"
    }
}
