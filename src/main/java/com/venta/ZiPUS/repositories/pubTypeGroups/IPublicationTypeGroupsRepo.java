package com.venta.ZiPUS.repositories.pubTypeGroups;

import com.venta.ZiPUS.models.publications.pubTypeGroups.PublicationTypeGroupModel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeGroupsRepo extends CrudRepository<PublicationTypeGroupModel, Long> {
    ArrayList<PublicationTypeGroupModel> findAll();
}
