package com.venta.zipus.repositories.pubTypes;

import com.venta.zipus.models.publications.pubtypegroups.PublicationTypeGroup;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

public interface IPublicationTypeRepo extends PagingAndSortingRepository<PublicationType, Long> {
    ArrayList<PublicationType> findAll();
    PublicationType findByPublicationTypeValue(String value);
    List<PublicationType> findByPublicationGroup(PublicationTypeGroup publicationGroup);
}
