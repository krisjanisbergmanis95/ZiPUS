package com.venta.ZiPUS.repositories;

import com.venta.ZiPUS.models.publications.PublicationBook;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationBookRepo extends CrudRepository<PublicationBook, Long> {
    ArrayList<PublicationBook> findAll();
}
