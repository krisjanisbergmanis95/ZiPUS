package com.venta.zipus.controllers.publication;

import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    IPublicationService publicationService;

    @GetMapping(value = "/add") // id for added publication
    public String getAddNewPublication() {
        return "add-publication-page";//add-publication-page
    }

    @GetMapping(value = "/{id}") // id for added publication
    public String getPublicationById(@PathVariable(name = "id") long id, Model model) {
        Publication pub= publicationService.getPublicationById(id);
        model.addAttribute("publication", pub);
        return "publication-page";
    }

    @GetMapping(value = "/my-publications") // id for added publication
    public String showMyPublications() {//@PathVariable(name = "user") User user, Model model
//        Publication pub = publicationService.getPublicationById(id);
//        model.addAttribute("publication", pub);
        return "my-publications-page";
    }
}
