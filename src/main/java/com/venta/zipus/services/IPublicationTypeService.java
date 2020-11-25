package com.venta.zipus.services;

import com.venta.zipus.models.publications.pubtypes.PublicationType;

public interface IPublicationTypeService {
    PublicationType getPubTypeByValue(String publicationTypeValue);
}
