package com.venta.ZiPUS.repositories.pubTypeGroups;

import com.venta.ZiPUS.models.publications.pubTypeGroups.PublicationTypeGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeGroupsRepo extends CrudRepository<PublicationTypeGroup, Long> {
    ArrayList<PublicationTypeGroup> findAll();
}
