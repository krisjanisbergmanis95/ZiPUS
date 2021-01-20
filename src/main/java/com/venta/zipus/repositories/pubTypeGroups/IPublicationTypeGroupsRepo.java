package com.venta.zipus.repositories.pubTypeGroups;

import com.venta.zipus.models.publications.pubtypegroups.PublicationTypeGroup;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface IPublicationTypeGroupsRepo extends PagingAndSortingRepository<PublicationTypeGroup, Long> {
    ArrayList<PublicationTypeGroup> findAll();
    PublicationTypeGroup findByPublicationTypesIn(ArrayList<PublicationType> publicationTypes);
    PublicationTypeGroup findByPubTypeGroupName(String pubTypeGroupName);
}
