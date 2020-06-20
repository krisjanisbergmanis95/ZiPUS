package com.venta.ZiPUS.controllers.publication;

import com.venta.ZiPUS.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.ZiPUS.repositories.pubTypes.IPublicationTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    IPublicationTypeRepo publicationTypeRepo;

    @Autowired
    IPublicationTypeGroupsRepo PublicationTypeGroupsRepo;


    @GetMapping("/add") // id for added publication
    public String getAddNewPublication() {
        return "add-publication-page";//add-publication-page
    }

    @GetMapping("/{id}") // id for added publication
    public String getPublicationById() {
        return "publication-page";//add-publication-page
    }
}
