package com.venta.zipus.controllers.publication;

import com.venta.zipus.controllers.user.UserController;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IPublicationService;
import com.venta.zipus.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.venta.zipus.helpers.UserHelper.getCurrentUsername;

@Controller
@RequestMapping("/publications")
public class PublicationController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IPublicationService publicationService;
    @Autowired
    IUserService userService;

    @GetMapping(value = "/add") // id for added publication
    public String getAddNewPublication() {
        return "add-publication-page-book";//add-publication-page
    }

    @GetMapping(value = "/{id}") // id for added publication
    public String getPublicationById(@PathVariable(name = "id") long id, Model model) {
        Publication pub = publicationService.getPublicationById(id);
        model.addAttribute("publication", pub);
        return "publication-page";
    }

    @GetMapping(value = "/my-publications") // id for added publication
    public String showMyPublications(Model model) {//@PathVariable(name = "user") User user, Model model
        User user = userService.getUserByUsername(getCurrentUsername());
        model.addAttribute("user", user);
        logger.info(user.getPublications().toString());
//        Publication pub = publicationService.getPublicationById(id);
//        model.addAttribute("publication", pub);
        return "my-publications-page";
    }
}
