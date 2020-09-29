package com.venta.zipus.repositories;

import com.venta.zipus.models.publications.Publication;
import org.springframework.data.repository.CrudRepository;

public interface IPublicationRepo extends CrudRepository<Publication, Long> {
    @Override
    Iterable<Publication> findAll();
    Publication findById(long id);
}
