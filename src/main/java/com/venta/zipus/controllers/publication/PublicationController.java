package com.venta.zipus.controllers.publication;

import com.venta.zipus.controllers.user.UserController;
import com.venta.zipus.helpers.SelectValues;
import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.publications.PublicationBook;
import com.venta.zipus.models.publications.PublicationConference;
import com.venta.zipus.models.publications.PublicationMagazine;
import com.venta.zipus.models.publications.pubtypegroups.constants.PublicationTypeGroupTitles;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IAuthorService;
import com.venta.zipus.services.IPublicationService;
import com.venta.zipus.services.IPublicationTypeService;
import com.venta.zipus.services.IUserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.List;

import static com.venta.zipus.helpers.SelectValues.PAGE_SIZES;
import static com.venta.zipus.helpers.UserHelper.getCurrentUsername;
import static java.util.Arrays.asList;

@Controller
@RequestMapping("/publications")
public class PublicationController {
    Logger logger = LoggerFactory.getLogger(PublicationController.class);
    @Autowired
    IPublicationService publicationService;

    @Autowired
    IUserService userService;

    @Autowired
    IPublicationTypeService publicationTypeService;

    @Autowired
    IAuthorService authorService;

    @GetMapping(value = "/add") // id for added publication
    public String showNewPublicationPage(Model model) {
        model.addAttribute("publicationTypeSelect", SelectValues.PUBLICATION_TYPES);
        return "add-publication-page";//add-publication-page
    }

    @GetMapping(value = "/add/magazine") // id for added publication
    public String showNewPublicationPageMagazine(Model model) {
        model.addAttribute("publication", new Publication());
        model.addAttribute("pubTypeMagazine", new PublicationMagazine());
        model.addAttribute("pubType", new PublicationType());
        model.addAttribute("pubTypes", publicationTypeService.getPubTypeByPublicationTypeTitle(PublicationTypeGroupTitles.GROUP_MAGAZINE));
        return "add-publication-page-magazine";
    }

    @PostMapping(value = "/add/magazine") // id for added publication
    public String addNewBookTypeMagazine(@Valid Publication publication,
                                         PublicationMagazine publicationMagazine,
                                         PublicationType pubType,
                                         BindingResult result,
                                         @RequestParam("file") MultipartFile file) throws IOException {

        if (!result.hasErrors()) {
            publication.setFilePath("upload-dir/" + file.getOriginalFilename());
            publication.setFileName(file.getOriginalFilename());
            publication.setPubFile(file.getBytes());
            PublicationType tempPubType = publicationTypeService.getPubTypeByValue(pubType.getPublicationTypeValue());
            logger.info("Adding pubType : " + tempPubType.toString());
            publication.setPubType(tempPubType);
            publicationTypeService.createPublicationMagazine(publicationMagazine);
            publication.setPublicationMagazine(publicationMagazine);
            User user = userService.getUserByUsername(getCurrentUsername());
            logger.info("Adding book, current user to link: " + user.toString());

            try {
                publication.addUser(user);
                logger.info("users " + publication.getUsers().toString() + " added");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            String spliter = publication.getAuthorsInput().contains(", ") ? ", " : ",";
            System.out.println("input : " + publication.getAuthorsInput());
            List<String> authorList = asList(publication.getAuthorsInput().split(spliter));
            System.out.println("list" + authorList);
            for (String listEntry : authorList) {
                String name = listEntry.split(" ")[0];
                String surname = listEntry.split(" ")[1];
                Author tempAuthor = new Author(name, surname, false, "");
                authorService.addNewAuthor(tempAuthor);
                Author theNewAuthor = authorService.findAuthorByNameAndSurname(name, surname);

                System.out.println("Author in db : " + theNewAuthor);
                try {
                    publication.addAuthor(theNewAuthor);
                } catch (Exception e) {
                    logger.error("Error while adding author to publication");
                    logger.error(e.getMessage());
                }
            }


            logger.info("=====================");
            logger.info(publication.toString());
            logger.info("=====================");

            publicationService.addPublication(publication);
            Publication newPub = publicationService
                    .getPublicationByTitleOriginAndTitleEnglish(
                            publication.getPublicationTitleOrigin(),
                            publication.getPublicationTitleEnglish());
            logger.info("=====================");
            logger.info(newPub.toString());
            logger.info("=====================");


            logger.info("Publication added successfully");
        } else {
            logger.error(result.toString());
        }
        return "redirect:/publications/my-publications/";//add-publication-page
    }

    @GetMapping(value = "/add/conference") // id for added publication
    public String showNewPublicationPageConference(Model model) {
        model.addAttribute("publication", new Publication());
        model.addAttribute("pubTypeConference", new PublicationConference());
        model.addAttribute("pubType", new PublicationType());
        model.addAttribute("pubTypes", publicationTypeService.getPubTypeByPublicationTypeTitle(PublicationTypeGroupTitles.GROUP_CONFERENCE));
        return "add-publication-page-conference";
    }

    @PostMapping(value = "/add/conference") // id for added publication
    public String addNewBookTypeConference(@Valid Publication publication,
                                           PublicationConference publicationConference,
                                           PublicationType pubType,
                                           BindingResult result,
                                           @RequestParam("file") MultipartFile file) throws IOException {

        if (!result.hasErrors()) {
            publication.setFilePath("upload-dir/" + file.getOriginalFilename());
            publication.setFileName(file.getOriginalFilename());
            publication.setPubFile(file.getBytes());
            PublicationType tempPubType = publicationTypeService.getPubTypeByValue(pubType.getPublicationTypeValue());
            logger.info("Adding pubType : " + tempPubType.toString());
            publication.setPubType(tempPubType);
            publicationTypeService.createPublicationConference(publicationConference);
            publication.setPublicationConference(publicationConference);
            User user = userService.getUserByUsername(getCurrentUsername());
            logger.info("Adding book, current user to link: " + user.toString());

            try {
                publication.addUser(user);
                logger.info("users " + publication.getUsers().toString() + " added");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            String spliter = publication.getAuthorsInput().contains(", ") ? ", " : ",";
            System.out.println("input : " + publication.getAuthorsInput());
            List<String> authorList = asList(publication.getAuthorsInput().split(spliter));
            System.out.println("list" + authorList);
            for (String listEntry : authorList) {
                String name = listEntry.split(" ")[0];
                String surname = listEntry.split(" ")[1];
                Author tempAuthor = new Author(name, surname, false, "");
                authorService.addNewAuthor(tempAuthor);
                Author theNewAuthor = authorService.findAuthorByNameAndSurname(name, surname);

                System.out.println("Author in db : " + theNewAuthor);
                try {
                    publication.addAuthor(theNewAuthor);
                } catch (Exception e) {
                    logger.error("Error while adding author to publication");
                    logger.error(e.getMessage());
                }
            }

            logger.info("=====================");
            logger.info(publication.toString());
            logger.info("=====================");

            publicationService.addPublication(publication);
            Publication newPub = publicationService
                    .getPublicationByTitleOriginAndTitleEnglish(
                            publication.getPublicationTitleOrigin(),
                            publication.getPublicationTitleEnglish());
            logger.info("=====================");
            logger.info(newPub.toString());
            logger.info("=====================");


            logger.info("Publication added successfully");
        } else {
            logger.error(result.toString());
        }
        return "redirect:/publications/my-publications/";//add-publication-page
    }


    @GetMapping(value = "/add/book") // id for added publication
    public String showNewPublicationPageBook(Model model) {
        model.addAttribute("publication", new Publication());
        model.addAttribute("pubTypeBook", new PublicationBook());
        model.addAttribute("pubType", new PublicationType());
        model.addAttribute("pubTypes", publicationTypeService.getPubTypeByPublicationTypeTitle(PublicationTypeGroupTitles.GROUP_BOOK));
        return "add-publication-page-book";//add-publication-page
    }

    @PostMapping(value = "/add/book") // id for added publication
    public String addNewBookTypePublication(@Valid Publication publication,
                                            PublicationBook publicationBook,
                                            PublicationType pubType,
                                            BindingResult result,
                                            @RequestParam("file") MultipartFile file) throws IOException {

        if (!result.hasErrors()) {
            publication.setFilePath("upload-dir/" + file.getOriginalFilename());
            publication.setFileName(file.getOriginalFilename());
            publication.setPubFile(file.getBytes());
            PublicationType tempPubType = publicationTypeService.getPubTypeByValue(pubType.getPublicationTypeValue());
            logger.info("Adding pubType : " + tempPubType.toString());
            publication.setPubType(tempPubType);
            publicationTypeService.createPublicationBook(publicationBook);
            publication.setPublicationBook(publicationBook);

            User user = userService.getUserByUsername(getCurrentUsername());
            logger.info("Adding book, current user to link: " + user.toString());

            try {
                publication.addUser(user);
                logger.info("users " + publication.getUsers().toString() + " added");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            String spliter = publication.getAuthorsInput().contains(", ") ? ", " : ",";
            System.out.println("input : " + publication.getAuthorsInput());
            List<String> authorList = asList(publication.getAuthorsInput().split(spliter));
            System.out.println("list" + authorList);
            for (String listEntry : authorList) {
                String name = listEntry.split(" ")[0];
                String surname = listEntry.split(" ")[1];
                Author tempAuthor = new Author(name, surname, false, "");
                authorService.addNewAuthor(tempAuthor);
                Author theNewAuthor = authorService.findAuthorByNameAndSurname(name, surname);

                System.out.println("Author in db : " + theNewAuthor);
                try {
                    publication.addAuthor(theNewAuthor);
                } catch (Exception e) {
                    logger.error("Error while adding author to publication");
                    logger.error(e.getMessage());
                }
            }

            logger.info("=====================");
            logger.info(publication.toString());
            logger.info("=====================");

            publicationService.addPublication(publication);
            Publication newPub = publicationService
                    .getPublicationByTitleOriginAndTitleEnglish(
                            publication.getPublicationTitleOrigin(),
                            publication.getPublicationTitleEnglish());
            logger.info("=====================");
            logger.info(newPub.toString());
            logger.info("=====================");

            logger.info("authors");
            logger.info(newPub.getAuthors().toString());
            logger.info("=====================");

            logger.info("Publication added successfully");
        } else {
            logger.error(result.toString());
        }
        return "redirect:/publications/my-publications/";//add-publication-page
    }

    @GetMapping(value = "/{id}") // id for added publication
    public String getPublicationById(@PathVariable(name = "id") long id, Model model) throws IOException {
        Publication pub = publicationService.getPublicationById(id);
        model.addAttribute("publication", pub);

        try (InputStream inputStream = new ByteArrayInputStream(pub.getPubFile());
             ByteArrayOutputStream targetStream = new ByteArrayOutputStream()) {
            IOUtils.copy(inputStream, targetStream);
            model.addAttribute("file", targetStream);
        }


        return "publication-page";
    }

    @GetMapping("/downloadFile/{pubId}")
    public HttpEntity<byte[]> downloadFile(@PathVariable long pubId) {
        Publication pub = publicationService.getPublicationById(pubId);
        logger.info(String.valueOf(pubId));
        logger.info(pub.getFileName());
        logger.info(pub.getPublicationTitleEnglish());
        byte[] documentBody = pub.getPubFile();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + pub.getFileName().replace(" ", "_"));
        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);
    }

    @GetMapping(value = "/my-publications")
    public String showMyPublications(Model model) {
        return showMyPublicationsPaginated(1, 5, "publicationTitleOrigin", String.valueOf(Sort.Direction.ASC), model);
    }

    @GetMapping(value = "/my-publications/page/{pageNum}/size/{pageSize}") // id for added publication
    public String showMyPublicationsPaginated(@PathVariable(value = "pageNum") int pageNum,
                                              @PathVariable(value = "pageSize") int pageSize,
                                              @RequestParam(value = "sortField") String sortField,
                                              @RequestParam(value = "sortDirection") String sortDirection,
                                              Model model) {//@PathVariable(name = "user") User user, Model model
        User user = userService.getUserByUsername(getCurrentUsername());

        Page<Publication> page = publicationService.findPublicationPageByUser(user, pageNum, pageSize, sortField, sortDirection);
        model = addModelAttributes(model, pageNum, page, pageSize, sortField, sortDirection);

        return "my-publications-page";
    }

    @GetMapping(value = "/my-publications/page/{pageNum}/size/{pageSize}/search")
    public String showMyPublicationsPaginatedSearchResults(@PathVariable(value = "pageNum") int pageNum,
                                                           @PathVariable(value = "pageSize") int pageSize,
                                                           @RequestParam(value = "searchValue") String searchValue,
                                                           @RequestParam(value = "sortField") String sortField,
                                                           @RequestParam(value = "sortDirection") String sortDirection,
                                                           Model model) {
        User user = userService.getUserByUsername(getCurrentUsername());

        Page<Publication> page = publicationService.findPublicationPageByUserISSNisbnOrTitle(user, pageNum, pageSize, sortField, sortDirection, searchValue);
        model = addModelAttributes(model, pageNum, page, pageSize, sortField, sortDirection);

        return "my-publications-page";
    }

    @GetMapping(value = "/")
    public String showPublicationsPage(Model model) {
        return findPaginated(1, 5, "publicationTitleOrigin", String.valueOf(Sort.Direction.ASC), model);
    }

    @GetMapping(value = "/page/{pageNum}/size/{pageSize}")
    public String findPaginated(@PathVariable(value = "pageNum") int pageNum,
                                @PathVariable(value = "pageSize") int pageSize,
                                @RequestParam(value = "sortField") String sortField,
                                @RequestParam(value = "sortDirection") String sortDirection,
                                Model model) {

        Page<Publication> page = publicationService.findPublicationPage(pageNum, pageSize, sortField, sortDirection);
        model = addModelAttributes(model, pageNum, page, pageSize, sortField, sortDirection);
        return "publication-list-page";
    }


    @GetMapping(value = "/page/{pageNum}/size/{pageSize}/search")
    public String getPublicationsSearchResults(@PathVariable(value = "pageNum") int pageNum,
                                               @PathVariable(value = "pageSize") int pageSize,
                                               @RequestParam(value = "searchValue") String searchValue,
                                               @RequestParam(value = "sortField") String sortField,
                                               @RequestParam(value = "sortDirection") String sortDirection,
                                               Model model) {

        Page<Publication> page = publicationService.findPublicationPageByISSNisbnOrTitle(pageNum, pageSize, sortField, sortDirection, searchValue);
        model = addModelAttributes(model, pageNum, page, pageSize, sortField, sortDirection);

        return "publication-list-page";
    }

    public Model addModelAttributes(Model model, int pageNum, Page page, int pageSize, String sortField, String sortDirection) {
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("selectedPageSize", pageSize);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals(String.valueOf(Sort.Direction.ASC)) ? String.valueOf(Sort.Direction.DESC) : String.valueOf(Sort.Direction.ASC));
        model.addAttribute("publications", page.getContent());
        model.addAttribute("allowedPageSizes", PAGE_SIZES);
        return model;
    }

    //http://localhost:8080/publications           /page/1/size/5?sortField=publicationTitleOrigin&sortDirection=ASC
    //http://localhost:8080/publications/authors/43/page/1/size/5?sortField=publicationTitleOrigin&sortDirection=ASC
    @GetMapping(value = "authors/{id}/page/{pageNum}/size/{pageSize}")
    public String showAuthorPageById(@PathVariable(name = "id") long id,
                                     @PathVariable(value = "pageNum") int pageNum,
                                     @PathVariable(value = "pageSize") int pageSize,
                                     @RequestParam(value = "sortField") String sortField,
                                     @RequestParam(value = "sortDirection") String sortDirection,
                                     Model model) {
        Author author = authorService.getAuthorById(id);
        System.out.println(author);
        //int pageSize = 5;
//        int pageNum = 1;
//        String sortField = "ASC";
//        String sortDirection = "ASC";

        Page<Publication> page = publicationService.findPublicationPageByAuthor(author, pageNum, pageSize, sortField, sortDirection);
        model = addModelAttributes(model, pageNum, page, pageSize, sortField, sortDirection);
        model.addAttribute("author", author);
        model.addAttribute("id", id);

        return "author-page";
    }
}
