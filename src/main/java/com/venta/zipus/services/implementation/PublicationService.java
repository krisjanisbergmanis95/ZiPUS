package com.venta.zipus.services.implementation;

import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.repositories.IPublicationRepo;
import com.venta.zipus.services.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
