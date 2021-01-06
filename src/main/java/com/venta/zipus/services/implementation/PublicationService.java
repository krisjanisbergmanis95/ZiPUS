package com.venta.zipus.services.implementation;


import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import com.venta.zipus.repositories.IPublicationRepo;
import com.venta.zipus.services.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PublicationService implements IPublicationService {

    @Autowired
    IPublicationRepo publicationRepo;

    @Override
    public Publication getPublicationById(long id) {
        if (publicationRepo.count() > 0) {
            return publicationRepo.findById(id);
        }
        return null;
    }

    @Override
    public boolean addPublication(Publication pub) {
        if (publicationRepo.existsByPublicationTitleOrigin(pub.getPublicationTitleOrigin())
                || publicationRepo.existsByPublicationTitleEnglish(pub.getPublicationTitleEnglish())) {
            return false;
        }
        Publication publication = new Publication(
                pub.getPubType(),
                pub.getLanguage(),
                pub.getPublicationTitleOrigin(),
                pub.getPublicationTitleEnglish(),
                pub.getAnnotation(),
                pub.getAnnotationEnglish(),
                pub.getFieldOfResearch(),
                (ArrayList) pub.getAuthors(),
                pub.getKeyWords(),
                pub.getPublisher(),
                pub.getPublishedYear(),
                pub.getPages(),
                pub.getIsbnISSN(),
//                pub.getPublishment(),
//                (ArrayList) pub.getDataBases(),
                pub.getHyperLink(),
                pub.getNotes(),
                pub.getPublicationBook(),
                pub.getFilePath(),
                pub.getFileName(),
                pub.getPubFile(),
                (ArrayList) pub.getUsers()
        );

        publicationRepo.save(publication);
        return true;
    }

    @Cacheable("pubRepo")
    public Publication getPublicationByTitleOriginAndTitleEnglish(String titleOrigin, String titleEnglish) {
        return publicationRepo.findByPublicationTitleOriginAndPublicationTitleEnglish(titleOrigin, titleEnglish);
    }

    public List<Publication> getPublicationsByUser(User user) {
        return getPublicationsByUser(new ArrayList<>(Arrays.asList(user)));
    }

    public List<Publication> getPublicationsByUser(ArrayList<User> users) {
        return publicationRepo.findByUsersIn(users);
    }

    public Page<Publication> findPublicationPage(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return publicationRepo.findAll(pageable);
    }

    public Page<Publication> findPublicationPageByISSNisbnOrTitle(int pageNum, int pageSize, String sortField, String sortDirection, String searchText) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        System.out.println(publicationRepo.findByQueryISBNOrTitle(pageable, searchText));
        return publicationRepo.findByQueryISBNOrTitle(pageable, searchText);
    }

    public Page<Publication> findPublicationPageByUser(User user, int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user));
        return publicationRepo.findByUsersIn(users, pageable);
    }
}
