package com.venta.zipus.services;

import com.venta.zipus.models.publications.Publication;

public interface IPublicationService {

    Publication getPublicationById(long id);

    boolean addPublication(Publication publication);

    Publication getPublicationByTitleOriginAndTitleEnglish(String publicationTitleOrigin, String publicationTitleEnglish);
}
