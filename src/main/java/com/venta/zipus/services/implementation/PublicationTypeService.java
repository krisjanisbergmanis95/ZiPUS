package com.venta.zipus.services.implementation;

import com.venta.zipus.models.publications.pubtypes.PublicationType;
import com.venta.zipus.repositories.pubTypes.IPublicationTypeRepo;
import com.venta.zipus.services.IPublicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationTypeService implements IPublicationTypeService {
    @Autowired
    IPublicationTypeRepo publicationTypeRepo;

    @Override
    public PublicationType getPubTypeByValue(String publicationTypeValue) {
        return publicationTypeRepo.findByPublicationTypeValue(publicationTypeValue);
    }
}
