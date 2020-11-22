package com.venta.zipus.repositories;

import com.venta.zipus.models.publications.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPublicationRepo extends PagingAndSortingRepository<Publication, Long> {
    @Override
    Page<Publication> findAll(Pageable pageable);
    Publication findById(long id);

    boolean existsByPublicationTitleOrigin(String publicationTitleOrigin);

    boolean existsByPublicationTitleEnglish(String publicationTitleEnglish);

    Publication findByPublicationTitleOriginAndPublicationTitleEnglish(String titleOrigin, String titleEnglish);
}
