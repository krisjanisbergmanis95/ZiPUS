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

//    SELECT * FROM PUBLICATIONUSERS PU JOIN PUBLICATION_TABLE PT ON PU.PUB_ID = PT.PUB_ID WHERE PU.UID = 28
//    AND (PT.PUBLICATIONTITLEORIGIN LIKE '%app%' OR PT.PUBLICATIONTITLEENGLISH LIKE '%app%' OR ISBNISSN LIKE '%app%');
    @Query(value = "SELECT * FROM PUBLICATION_TABLE pt" +
            " JOIN PUBLICATIONUSERS pu ON pu.PUB_ID = pt.PUB_ID" +
            " WHERE pu.UID = :userId" +
            " AND (pt.PUBLICATIONTITLEORIGIN LIKE CONCAT('%', :searchText, '%')" +
            " OR pt.PUBLICATIONTITLEENGLISH LIKE CONCAT('%', :searchText, '%')" +
            " OR pt.ISBNISSN LIKE CONCAT('%', :searchText, '%'))" +
            " ORDER BY pt.PUBLICATIONTITLEORIGIN",
            nativeQuery = true)

    Page<Publication> findByQueryUserAndISBNOrTitle(Pageable pageable, @Param("userId") long userId, @Param("searchText") String searchText);
}
