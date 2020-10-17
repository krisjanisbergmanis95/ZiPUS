package com.venta.zipus.controllers.publication;

import com.venta.zipus.controllers.fileupload.FileUploadController;
import com.venta.zipus.controllers.user.UserController;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.publications.PublicationBook;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IPublicationService;
import com.venta.zipus.services.IUserService;
import com.venta.zipus.services.implementation.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.venta.zipus.services.IStorageService;
import com.venta.zipus.localstorage.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.stream.Collectors;

import static com.venta.zipus.helpers.UserHelper.getCurrentUsername;

@Controller
@RequestMapping("/publications")
public class PublicationController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IPublicationService publicationService;
    @Autowired
    IUserService userService;
    @Autowired
    IStorageService storageService;

    @GetMapping(value = "/add") // id for added publication
    public String showNewPublicationPage(Model model) {
        model.addAttribute("publication", new Publication());
        model.addAttribute("pubTypeBook", new PublicationBook());
        model.addAttribute("pubType", new PublicationType());
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "add-publication-page-book";//add-publication-page
    }

    @PostMapping(value = "/add/book") // id for added publication
    public String addNewBookTypePublication(@Valid Publication publication,
                                            PublicationBook publicationBook,
                                            BindingResult result,
                                            @RequestParam("file") MultipartFile file) {
        logger.info(publication.toString());
        if (!result.hasErrors()) {
            publicationService.addPublication(publication);
            Publication newPub = publicationService
                    .getPublicationByTitleOriginAndTitleEnglish(
                            publication.getPublicationTitleOrigin(),
                            publication.getPublicationTitleEnglish());

            User user = userService.getUserByUsername(getCurrentUsername());
            try {
                user.addPublication(newPub);
                storageService.store(file);
                userService.updateUser(user);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            logger.info("Publication added successfully");
        } else {
            logger.error("Something wrong?");
            logger.error(result.toString());
        }
        return "redirect:/publications/my-publications/";//add-publication-page
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
