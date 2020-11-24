package com.venta.zipus.repositories;

import com.venta.zipus.models.publications.PublicationConference;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPublicationConferenceRepo extends PagingAndSortingRepository<PublicationConference, Long> {
}
