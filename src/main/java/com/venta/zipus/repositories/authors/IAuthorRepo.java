package com.venta.zipus.repositories.authors;

import com.venta.zipus.models.authors.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface IAuthorRepo extends PagingAndSortingRepository<Author, Long> {
    ArrayList<Author> findAll();
    Author findById(long id);
}
