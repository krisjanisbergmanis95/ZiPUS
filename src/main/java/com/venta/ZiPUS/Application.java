package com.venta.ZiPUS;

import com.venta.ZiPUS.models.publications.pubTypeGroups.PublicationTypeGroup;
import com.venta.ZiPUS.models.publications.pubTypeGroups.constants.PublicationTypeGroupTitles;
import com.venta.ZiPUS.models.publications.pubTypes.PublicationType;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesConferences;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesBook;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesMagazine;
import com.venta.ZiPUS.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.ZiPUS.repositories.pubTypes.IPublicationTypeRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Autowired
    IPublicationTypeRepo publicationTypeRepo;

    @Autowired
    IPublicationTypeGroupsRepo publicationTypeGroupsRepo;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            PublicationTypeGroup pubGroupTypeBook = new PublicationTypeGroup(PublicationTypeGroupTitles.GROUP_BOOK);
            PublicationTypeGroup pubGroupTypeConference = new PublicationTypeGroup(PublicationTypeGroupTitles.GROUP_CONFERENCE);
            PublicationTypeGroup pubGroupTypeMagazine = new PublicationTypeGroup(PublicationTypeGroupTitles.GROUP_MAGAZINE);
            publicationTypeGroupsRepo.save(pubGroupTypeBook);
            publicationTypeGroupsRepo.save(pubGroupTypeConference);
            publicationTypeGroupsRepo.save(pubGroupTypeMagazine);

            publicationTypeRepo.save(new PublicationType(PublicationTypeTitlesBook.EDUCATIONAL_BOOK, pubGroupTypeBook));
            publicationTypeRepo.save(new PublicationType(PublicationTypeTitlesBook.PUB_IN_SC_PAPER_COLLECTION, pubGroupTypeBook));
            publicationTypeRepo.save(new PublicationType(PublicationTypeTitlesBook.SECTION_IN_SC_MONOGRAPH, pubGroupTypeBook));
            publicationTypeRepo.save(new PublicationType(PublicationTypeTitlesBook.SC_MONOGRAPH, pubGroupTypeBook));
            publicationTypeRepo.save(new PublicationType(PublicationTypeTitlesConferences.ARTICLE_IN_CONFERENCE_COLLECTION, pubGroupTypeConference));
            publicationTypeRepo.save(new PublicationType(PublicationTypeTitlesConferences.ARTICLE_IN_THESIS_COLLECTION, pubGroupTypeConference));
            publicationTypeRepo.save(new PublicationType(PublicationTypeTitlesMagazine.PUB_IN_SC_MAGAZINE, pubGroupTypeMagazine));
        };
    }
}
