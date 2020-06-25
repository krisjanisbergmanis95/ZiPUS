package com.venta.ZiPUS.services.implementation;

import com.venta.ZiPUS.models.publications.Publication;
import com.venta.ZiPUS.repositories.IPublicationRepo;
import com.venta.ZiPUS.services.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class publicationService implements IPublicationService {

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
