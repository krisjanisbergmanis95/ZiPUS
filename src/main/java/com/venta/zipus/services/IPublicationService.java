package com.venta.zipus.services;

import com.venta.zipus.models.publications.Publication;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface IPublicationService {

    Publication getPublicationById(long id);

    boolean addPublication(Publication publication);
    boolean storeFileAsByteArray(MultipartFile file);
//    boolean storeFileAsByteArray(MultipartFile file);

    Publication getPublicationByTitleOriginAndTitleEnglish(String publicationTitleOrigin, String publicationTitleEnglish);

    Page<Publication> findPublicationPage(int pageNum, int pageSize);
}
