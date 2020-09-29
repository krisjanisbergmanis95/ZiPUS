package com.venta.zipus.repositories.pubTypes;

import com.venta.zipus.models.publications.pubtypes.PublicationType;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeRepo extends CrudRepository<PublicationType, Long> {
    ArrayList<PublicationType> findAll();
    PublicationType findByPublicationTypeValue(String value);
}
