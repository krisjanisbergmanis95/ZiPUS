package com.venta.zipus.services;

import com.venta.zipus.models.publications.PublicationBook;
import com.venta.zipus.models.publications.PublicationConference;
import com.venta.zipus.models.publications.PublicationMagazine;
import com.venta.zipus.models.publications.pubtypegroups.PublicationTypeGroup;
import com.venta.zipus.models.publications.pubtypes.PublicationType;

import java.util.List;

public interface IPublicationTypeService {
    PublicationType getPubTypeByValue(String publicationTypeValue);
    List<PublicationType> getPubTypeByPublicationTypeTitle(String publicationTypeGroup);
    void createPublicationBook(PublicationBook publicationBook);
    void createPublicationMagazine(PublicationMagazine publicationMagazine);
    void createPublicationConference(PublicationConference publicationConference);
}
