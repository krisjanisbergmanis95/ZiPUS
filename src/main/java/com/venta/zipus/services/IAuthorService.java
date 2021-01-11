package com.venta.zipus.services;

import com.venta.zipus.models.authors.Author;

import java.util.List;

public interface IAuthorService {
    Author getAuthorById(long id);

    Author findAuthorByNameAndSurname(String name, String surname);
    boolean addNewAuthor(Author author);
    List<Author> getAllAuthors();
}
