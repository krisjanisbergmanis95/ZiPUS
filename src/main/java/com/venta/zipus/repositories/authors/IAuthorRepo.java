package com.venta.zipus.repositories.authors;

import com.venta.zipus.models.authors.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

public interface IAuthorRepo extends PagingAndSortingRepository<Author, Long> {
    List<Author> findAll();
    Author findById(long id);
    Author findAuthorByNameAndSurname(String name, String surname);
    boolean existsByNameAndSurname(String name, String surname);
}
