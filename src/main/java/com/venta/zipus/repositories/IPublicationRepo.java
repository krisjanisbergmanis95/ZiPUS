package com.venta.zipus.repositories;

import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface IPublicationRepo extends PagingAndSortingRepository<Publication, Long> {
    @Override
    Page<Publication> findAll(Pageable pageable);
    Publication findById(long id);

    boolean existsByPublicationTitleOrigin(String publicationTitleOrigin);

    boolean existsByPublicationTitleEnglish(String publicationTitleEnglish);
    List<Publication> findByUsersIn(ArrayList<User> user);
    Page<Publication> findByUsersIn(ArrayList<User> user, Pageable pageable);
    Publication findByPublicationTitleOriginAndPublicationTitleEnglish(String titleOrigin, String titleEnglish);
//SELECT * FROM Publication_table pt WHERE pt.PUBLICATIONTITLEORIGIN LIKE '%CC%' OR pt.PUBLICATIONTITLEENGLISH LIKE '%test%';
    @Query(value = "SELECT * FROM Publication_table pt " +
            "WHERE pt.PUBLICATIONTITLEORIGIN " +
            "LIKE CONCAT('%', :searchText, '%')" +
            " OR pt.PUBLICATIONTITLEENGLISH" +
            " LIKE CONCAT('%', :searchText, '%')" +
            " OR pt.ISBNISSN" +
            " LIKE CONCAT('%', :searchText, '%')",
            nativeQuery = true)
    Page<Publication> findByQueryISBNOrTitle(Pageable pageable, @Param("searchText") String searchText);
}
