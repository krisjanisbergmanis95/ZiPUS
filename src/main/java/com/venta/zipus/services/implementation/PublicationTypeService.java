package com.venta.zipus.services.implementation;

import com.venta.zipus.models.publications.PublicationBook;
import com.venta.zipus.models.publications.PublicationConference;
import com.venta.zipus.models.publications.PublicationMagazine;
import com.venta.zipus.models.publications.pubtypegroups.PublicationTypeGroup;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import com.venta.zipus.repositories.IPublicationBookRepo;
import com.venta.zipus.repositories.IPublicationConferenceRepo;
import com.venta.zipus.repositories.IPublicationMagazineRepo;
import com.venta.zipus.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.zipus.repositories.pubTypes.IPublicationTypeRepo;
import com.venta.zipus.services.IPublicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationTypeService implements IPublicationTypeService {
    @Autowired
    IPublicationTypeRepo publicationTypeRepo;

    @Autowired
    IPublicationBookRepo publicationBookRepo;
    @Autowired
    IPublicationMagazineRepo publicationMagazineRepo;
    @Autowired
    IPublicationConferenceRepo publicationConferenceRepo;

    @Autowired
    IPublicationTypeGroupsRepo publicationTypeGroupsRepo;

    @Override
    public PublicationType getPubTypeByValue(String publicationTypeValue) {
        return publicationTypeRepo.findByPublicationTypeValue(publicationTypeValue);
    }

    public List<PublicationType> getPubTypeByPublicationTypeTitle(String pubTypeGroupName) {
        return publicationTypeRepo.findByPublicationGroup(publicationTypeGroupsRepo.findByPugTypeGroupName(pubTypeGroupName));
    }

    public void createPublicationBook(PublicationBook publicationBook) {
        publicationBookRepo.save(publicationBook);
    }

    @Override
    public void createPublicationMagazine(PublicationMagazine publicationMagazine) {
        publicationMagazineRepo.save(publicationMagazine);
    }

    @Override
    public void createPublicationConference(PublicationConference publicationConference) {
        publicationConferenceRepo.save(publicationConference);
    }

}
