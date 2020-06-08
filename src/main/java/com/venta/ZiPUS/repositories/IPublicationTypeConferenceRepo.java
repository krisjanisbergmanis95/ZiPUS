package com.venta.ZiPUS.repositories;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModelConference;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublicationTypeConferenceRepo extends CrudRepository<PublicationTypeModelConference, Long> {
    ArrayList<PublicationTypeModelConference> findAll();
}
