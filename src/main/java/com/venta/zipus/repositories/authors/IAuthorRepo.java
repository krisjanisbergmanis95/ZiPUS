package com.venta.zipus.repositories.authors;

import com.venta.zipus.models.authors.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IAuthorRepo extends CrudRepository<Author, Long> {
    ArrayList<Author> findAll();
    Author findById(long id);
}
