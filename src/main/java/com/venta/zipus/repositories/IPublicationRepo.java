package com.venta.zipus.repositories;

import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

public interface IPublicationRepo extends PagingAndSortingRepository<Publication, Long> {
    @Override
    Page<Publication> findAll(Pageable pageable);
    Publication findById(long id);

    boolean existsByPublicationTitleOrigin(String publicationTitleOrigin);

    boolean existsByPublicationTitleEnglish(String publicationTitleEnglish);
    List<Publication> findByUsersIn(ArrayList<User> user);
    Publication findByPublicationTitleOriginAndPublicationTitleEnglish(String titleOrigin, String titleEnglish);
}
