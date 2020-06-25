package com.venta.ZiPUS.repositories.authors;

import com.venta.ZiPUS.models.Authors.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IAuthorRepo extends CrudRepository<Author, Long> {
    ArrayList<Author> findAll();
    Author findById(long id);
}
