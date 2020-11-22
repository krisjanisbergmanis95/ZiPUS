package com.venta.zipus.services.implementation;


import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.repositories.IPublicationRepo;
import com.venta.zipus.services.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class PublicationService implements IPublicationService {

    @Autowired
    IPublicationRepo publicationRepo;

    @Override
    @Cacheable("publications")
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
                pub.getPubFile()
        );

        publicationRepo.save(publication);
        return true;
    }

    @Override
    public boolean storeFileAsByteArray(MultipartFile file) {
//        publicationRepo
        return false;
    }

    @Cacheable("publications")
    public Publication getPublicationByTitleOriginAndTitleEnglish(String titleOrigin, String titleEnglish) {
        return publicationRepo.findByPublicationTitleOriginAndPublicationTitleEnglish(titleOrigin, titleEnglish);
    }


    public Page<Publication> findPublicationPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return publicationRepo.findAll(pageable);
    }
}
