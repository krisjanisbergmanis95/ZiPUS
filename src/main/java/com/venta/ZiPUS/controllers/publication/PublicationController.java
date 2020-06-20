package com.venta.ZiPUS.controllers.publication;

import com.venta.ZiPUS.models.publications.Publication;
import com.venta.ZiPUS.repositories.IPublicationRepo;
import com.venta.ZiPUS.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.ZiPUS.repositories.pubTypes.IPublicationTypeRepo;
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
    IPublicationTypeRepo publicationTypeRepo;

    @Autowired
    IPublicationTypeGroupsRepo PublicationTypeGroupsRepo;

    @Autowired
    IPublicationRepo publicationRepo;

    @GetMapping(value = "/add") // id for added publication
    public String getAddNewPublication() {
        return "add-publication-page";//add-publication-page
    }

    @GetMapping(value = "/{id}") // id for added publication
    public String getPublicationById(@PathVariable(name = "id") long id, Model model) {
        Publication temp = publicationRepo.findById(id);
        model.addAttribute("publication", temp);
        return "publication-page";//add-publication-page
    }
}
