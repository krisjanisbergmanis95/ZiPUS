package com.venta.ZiPUS.repositories;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModelMagazine;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeMagazineRepo extends CrudRepository<PublicationTypeModelMagazine, Long> {
    ArrayList<PublicationTypeModelMagazine> findAll();
}
