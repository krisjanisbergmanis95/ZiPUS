package com.venta.zipus.repositories;

import com.venta.zipus.models.publications.PublicationBook;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface IPublicationBookRepo extends PagingAndSortingRepository<PublicationBook, Long> {
    ArrayList<PublicationBook> findAll();
}
