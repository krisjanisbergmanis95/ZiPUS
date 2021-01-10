package com.venta.zipus.services.implementation;

import com.venta.zipus.models.authors.Author;
import com.venta.zipus.repositories.authors.IAuthorRepo;
import com.venta.zipus.services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    IAuthorRepo authorRepo;

    @Override
    public Author getAuthorById(long id) {
       return authorRepo.findById(id);
    }
}
