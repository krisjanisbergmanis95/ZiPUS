package com.venta.zipus.controllers.author;

import com.venta.zipus.config.WebSecurityConfig;
import com.venta.zipus.controllers.AppController;
import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IAuthorService;
import com.venta.zipus.services.IPublicationService;
import com.venta.zipus.services.IUserAuthorityService;
import com.venta.zipus.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import static com.venta.zipus.helpers.UserHelper.getCurrentUsername;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    IAuthorService authorService;

    @Autowired
    IUserService userService;


    @Autowired
    IUserAuthorityService userAuthorityService;
//@{'authors/' + ${id} + '/publications/page/' + ${currentPage} + '/size/' + ${selectedPageSize} + '?sortField=IsbnISSN&sortDirection=' + ${reverseSortDirection}}

    @GetMapping("/")
    public String showAllUsersPage(Model model) {
        User user = userService.getUserByUsername(getCurrentUsername(), false);
        logger.info(user.toString());

        Boolean isAuthorityZUAAD = user.isAuthority(
                userAuthorityService.getUserAuthorityByTitle(WebSecurityConfig.ZUADD));
        logger.info("is isAuthorityZUAAD = " + isAuthorityZUAAD);
        logger.info(user.getAuthorities().toString());
        logger.info(userAuthorityService.getUserAuthorityByTitle(WebSecurityConfig.ADMIN).getRoleTitle());
        if (isAuthorityZUAAD) {
            model.addAttribute("isAuthorityZUAAD", isAuthorityZUAAD);
            logger.info("loading author list");
            System.out.println(authorService.getAllAuthors());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "authors-list";
        }
        return "error";
    }
}
