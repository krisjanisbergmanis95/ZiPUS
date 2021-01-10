package com.venta.zipus.controllers.author;

import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.services.IAuthorService;
import com.venta.zipus.services.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/authors")
public class AuthorController {


    @Autowired
    IPublicationService publicationService;
//@{'authors/' + ${id} + '/publications/page/' + ${currentPage} + '/size/' + ${selectedPageSize} + '?sortField=IsbnISSN&sortDirection=' + ${reverseSortDirection}}

}
