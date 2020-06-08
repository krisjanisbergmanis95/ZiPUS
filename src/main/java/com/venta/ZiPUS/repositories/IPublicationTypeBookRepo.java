package com.venta.ZiPUS.repositories;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModelBook;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeBookRepo extends CrudRepository<PublicationTypeModelBook, Long> {
    ArrayList<PublicationTypeModelBook> findAll();
}
