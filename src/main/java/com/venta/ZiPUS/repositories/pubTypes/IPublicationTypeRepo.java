package com.venta.ZiPUS.repositories.pubTypes;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeRepo extends CrudRepository<PublicationTypeModel, Long> {
    ArrayList<PublicationTypeModel> findAll();
}
