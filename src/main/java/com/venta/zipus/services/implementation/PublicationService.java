package com.venta.zipus.services.implementation;

import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.databases.DataBase;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.publications.PublicationBook;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import com.venta.zipus.models.publishments.PublishmentType;
import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.IPublicationRepo;
import com.venta.zipus.services.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.venta.zipus.WebSecurityConfig.passwordEncoder;

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
                pub.getFileName()
        );

        publicationRepo.save(publication);
        return true;
    }

    public Publication getPublicationByTitleOriginAndTitleEnglish(String titleOrigin, String titleEnglish) {
        return publicationRepo.findByPublicationTitleOriginAndPublicationTitleEnglish(titleOrigin, titleEnglish);
    }
}
