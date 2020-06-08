package com.venta.ZiPUS.repositories.pubTypes;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationType;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeRepo extends CrudRepository<PublicationType, Long> {
    ArrayList<PublicationType> findAll();
}
