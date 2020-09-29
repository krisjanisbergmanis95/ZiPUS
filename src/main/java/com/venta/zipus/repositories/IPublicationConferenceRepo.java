package com.venta.zipus.repositories;

import com.venta.zipus.models.publications.PublicationConference;
import org.springframework.data.repository.CrudRepository;

public interface IPublicationConferenceRepo extends CrudRepository<PublicationConference, Long> {
}
