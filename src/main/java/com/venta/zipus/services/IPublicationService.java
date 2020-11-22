package com.venta.zipus.services;

import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface IPublicationService {

    Publication getPublicationById(long id);

    boolean addPublication(Publication publication);
    boolean storeFileAsByteArray(MultipartFile file);
//    boolean storeFileAsByteArray(MultipartFile file);

    Publication getPublicationByTitleOriginAndTitleEnglish(String publicationTitleOrigin, String publicationTitleEnglish);

    Page<Publication> findPublicationPage(int pageNum, int pageSize);
    List<Publication> getPublicationsByUser(User user);
    List<Publication> getPublicationsByUser(ArrayList<User> users);
}
