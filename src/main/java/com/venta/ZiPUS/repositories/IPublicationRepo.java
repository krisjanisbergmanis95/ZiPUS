package com.venta.ZiPUS.repositories;

import com.venta.ZiPUS.models.publications.Publication;
import org.springframework.data.repository.CrudRepository;

public interface IPublicationRepo extends CrudRepository<Publication, Long> {
    @Override
    Iterable<Publication> findAll();
}
