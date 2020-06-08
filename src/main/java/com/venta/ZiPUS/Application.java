package com.venta.ZiPUS;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModelBook;
import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModelConference;
import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModelMagazine;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitleConferences;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesBook;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesMagazine;
import com.venta.ZiPUS.repositories.IPublicationTypeBookRepo;
import com.venta.ZiPUS.repositories.IPublicationTypeConferenceRepo;
import com.venta.ZiPUS.repositories.IPublicationTypeMagazineRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Autowired
    IPublicationTypeBookRepo publicationTypeRepoBook;

    @Autowired
    IPublicationTypeConferenceRepo publicationTypeRepoConference;

    @Autowired
    IPublicationTypeMagazineRepo publicationTypeMagazine;
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            publicationTypeRepoBook.save(new PublicationTypeModelBook(PublicationTypeTitlesBook.EDUCATIONAL_BOOK));
            publicationTypeRepoBook.save(new PublicationTypeModelBook(PublicationTypeTitlesBook.PUB_IN_SC_PAPER_COLLECTION));
            publicationTypeRepoBook.save(new PublicationTypeModelBook(PublicationTypeTitlesBook.SECTION_IN_SC_MONOGRAPH));
            publicationTypeRepoBook.save(new PublicationTypeModelBook(PublicationTypeTitlesBook.SC_MONOGRAPH));
            publicationTypeRepoConference.save(new PublicationTypeModelConference(PublicationTypeTitleConferences.ARTICLE_IN_CONFERENCE_COLLECTION));
            publicationTypeRepoConference.save(new PublicationTypeModelConference(PublicationTypeTitleConferences.ARTICLE_IN_THESIS_COLLECTION));
            publicationTypeMagazine.save(new PublicationTypeModelMagazine(PublicationTypeTitlesMagazine.PUB_IN_SC_MAGAZINE));
        };
    }
}
