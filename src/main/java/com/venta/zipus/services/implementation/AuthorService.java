package com.venta.zipus.services.implementation;

import com.venta.zipus.models.authors.Author;
import com.venta.zipus.repositories.authors.IAuthorRepo;
import com.venta.zipus.services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    IAuthorRepo authorRepo;

    @Override
    public Author getAuthorById(long id) {
       return authorRepo.findById(id);
    }

    @Override
    public Author findAuthorByNameAndSurname(String name, String surname) {
        if (authorRepo.existsByNameAndSurname(name, surname)) {
            return authorRepo.findAuthorByNameAndSurname(name, surname );
        }
       return null;
    }

    @Override
    public boolean addNewAuthor(Author author) {
        if (authorRepo.existsByNameAndSurname(author.getName(), author.getSurname())) {
            return false;
        }

        Author newAuthor = new Author(
                author.getName(),
                author.getSurname(),
                author.isWorkingInVea(),
                author.getInstitution()
        );

        authorRepo.save(newAuthor);
        return true;
    }

    @Override
    public List<Author> getAllAuthors() {
       return authorRepo.findAll();
    }
}
