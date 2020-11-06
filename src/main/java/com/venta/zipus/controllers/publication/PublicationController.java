package com.venta.zipus.controllers.publication;

import com.venta.zipus.controllers.fileupload.FileUploadController;
import com.venta.zipus.controllers.user.UserController;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.publications.PublicationBook;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IPublicationService;
import com.venta.zipus.services.IUserService;
import javassist.bytecode.ByteArray;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.venta.zipus.services.IStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Path;

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
        return "add-publication-page-book";//add-publication-page
    }

    @PostMapping(value = "/add/book") // id for added publication
    public String addNewBookTypePublication(@Valid Publication publication,
                                            PublicationBook publicationBook,
                                            BindingResult result,
                                            @RequestParam("file") MultipartFile file) throws IOException {

        if (!result.hasErrors()) {
            publication.setFilePath("upload-dir/" + file.getOriginalFilename());
            publication.setFileName(file.getOriginalFilename());
//            publication.setPubFile(file);
            publication.setPubFile(file.getBytes());
            publicationService.addPublication(publication);
            Publication newPub = publicationService
                    .getPublicationByTitleOriginAndTitleEnglish(
                            publication.getPublicationTitleOrigin(),
                            publication.getPublicationTitleEnglish());
            logger.info("=====================");
            logger.info(publication.toString());
            logger.info("=====================");

            User user = userService.getUserByUsername(getCurrentUsername());
            try {
                user.addPublication(newPub);
//                storageService.store(file);
                publicationService.storeFileAsByteArray(file);

                logger.info("file stored");
                userService.updateUser(user);
                logger.info("user updated");
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
    public String getPublicationById(@PathVariable(name = "id") long id, Model model) throws IOException {
        Publication pub = publicationService.getPublicationById(id);
        model.addAttribute("publication", pub);
//        logger.info("loading from path " + pub.getFilePath());
//
//        Path path = storageService.load(pub.getFilePath());
//        String pathFileName = path.getFileName().toString();
//        logger.info("pathFileName " + pathFileName);
//

//        InputStream inputStream = new ByteArrayInputStream(pub.getPubFile());

        try (InputStream inputStream = new ByteArrayInputStream(pub.getPubFile());
             ByteArrayOutputStream targetStream = new ByteArrayOutputStream())
        {
            IOUtils.copy(inputStream, targetStream);
//            FileWriter multipartFile = new CommonsMultipartFile(targetStream);
            model.addAttribute("file", targetStream);
        }


//        MultipartFile file = new MockMultipartFile(pub.getFileName(), pub.getFileName(), MediaType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//        file.transferTo(new File(pub.getFilePath()));
//        Path path = storageService.load(pub.getFilePath());
//        String pathFileName = path.getFileName().toString();

//        ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attchment:filename=\"" + pub.getFileName() + "\"")
//        .body(file);
//        model.addAttribute("file", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                "serveFile", pathFileName).build().toUri().toString());
//        model.addAttribute("file", new ByteArrayResource(pub.getPubFile()));
//        model.addAttribute("file", file.transferTo().getURI().toString());
//        model.addAttribute("file", ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attchment:filename=\"" + pub.getFileName() + "\"")
//                .body(new ByteArrayResource(pub.getPubFile())));
        return "publication-page";
    }

    @GetMapping("/downloadFile/{pubId}")
//    public String downloadFile(@PathVariable long pubId) {
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
