package com.venta.zipus.services;

import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;

public interface IPublicationService {

    Publication getPublicationById(long id);

    boolean addPublication(Publication publication);

    Publication getPublicationByTitleOriginAndTitleEnglish(String publicationTitleOrigin, String publicationTitleEnglish);

    Page<Publication> findPublicationPage(int pageNum, int pageSize, String sortField, String sortDirection);
    Page<Publication> findPublicationPageByUser(User user, int pageNum, int pageSize, String sortField, String sortDirection);
    Page<Publication> findPublicationPageByAuthor(Author author, int pageNum, int pageSize, String sortField, String sortDirection);
    Page<Publication> findPublicationPageByISSNisbnOrTitle(int pageNum, int pageSize, String sortField, String sortDirection, String searchText);
    Page<Publication> findPublicationPageByUserISSNisbnOrTitle(User user, int pageNum, int pageSize, String sortField, String sortDirection, String searchText);
    List<Publication> getPublicationsByUser(User user);
    List<Publication> getPublicationsByUser(ArrayList<User> users);
}
