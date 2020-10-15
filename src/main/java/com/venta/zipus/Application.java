package com.venta.zipus;

import com.venta.zipus.models.databases.DataBase;
import com.venta.zipus.models.databases.constants.DataBaseNames;
import com.venta.zipus.models.publications.pubtypegroups.PublicationTypeGroup;
import com.venta.zipus.models.publications.pubtypegroups.constants.PublicationTypeGroupTitles;
import com.venta.zipus.models.publications.pubtypes.PublicationType;
import com.venta.zipus.models.publications.pubtypes.constants.PublicationTypeTitlesConferences;
import com.venta.zipus.models.publications.pubtypes.constants.PublicationTypeTitlesBook;
import com.venta.zipus.models.publications.pubtypes.constants.PublicationTypeTitlesMagazine;
import com.venta.zipus.models.publishments.PublishmentType;
import com.venta.zipus.models.publishments.constants.PublishmentTypeNames;
import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.dataBases.IDataBaseRepo;
import com.venta.zipus.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.zipus.repositories.pubTypes.IPublicationTypeRepo;
import com.venta.zipus.repositories.publishments.IPublishmentRepo;
import com.venta.zipus.repositories.user.IUserAuthorityRepo;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IStorageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

import static com.venta.zipus.WebSecurityConfig.passwordEncoder;

@SpringBootApplication
public class Application {
    @Autowired
    IPublicationTypeRepo publicationTypeRepo;

    @Autowired
    IPublicationTypeGroupsRepo publicationTypeGroupsRepo;

    @Autowired
    IDataBaseRepo dataBaseRepo;

    @Autowired
    IPublishmentRepo publishmentRepo;

    @Autowired
    IUserAuthorityRepo userAuthorityRepo;

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

            dataBaseRepo.save(new DataBase(DataBaseNames.EBSCO));
            dataBaseRepo.save(new DataBase(DataBaseNames.ENGINEERING_VILLAGE_2));
            dataBaseRepo.save(new DataBase(DataBaseNames.ERIH));
            dataBaseRepo.save(new DataBase(DataBaseNames.GOOGLE_SCHOLAR));
            dataBaseRepo.save(new DataBase(DataBaseNames.IEEE_XPLORE));
            dataBaseRepo.save(new DataBase(DataBaseNames.SCOPUS));
            dataBaseRepo.save(new DataBase(DataBaseNames.SPRINGER_LINK));
            dataBaseRepo.save(new DataBase(DataBaseNames.THOMSON_WEB_OF_SC));

            publishmentRepo.save(new PublishmentType(PublishmentTypeNames.INTERNATIONAL_REVIEW));
            publishmentRepo.save(new PublishmentType(PublishmentTypeNames.NATIONAL_REVIEW));
            publishmentRepo.save(new PublishmentType(PublishmentTypeNames.OTHER));


            userAuthorityRepo.save(new UserAuthority(WebSecurityConfig.USER));
            userAuthorityRepo.save(new UserAuthority(WebSecurityConfig.ADMIN));
            userAuthorityRepo.save(new UserAuthority(WebSecurityConfig.AUTHOR));
            userAuthorityRepo.save(new UserAuthority(WebSecurityConfig.ZUADD));
        };
    }

    @Bean
    public CommandLineRunner setUpUsers(IUserAuthorityRepo userAuthorityRepo, IUserRepo userRepo) {
        return (args) -> {
            UserAuthority AuthTypeUser = userAuthorityRepo.findByRoleTitle(WebSecurityConfig.USER);
            User u1 = new User("user1", passwordEncoder().encode("user1"), AuthTypeUser);
            userRepo.save(u1);

            UserAuthority AuthTypeAdmin = userAuthorityRepo.findByRoleTitle(WebSecurityConfig.ADMIN);
            User u2 = new User("admin", passwordEncoder().encode("admin"), new ArrayList<>(Arrays.asList(AuthTypeAdmin)));
            userRepo.save(u2);

            UserAuthority AuthTypeAuthor = userAuthorityRepo.findByRoleTitle(WebSecurityConfig.AUTHOR);
            User u3 = new User("john",
                    "doe",
                    "doo",
                    "doe@dank.com",
                    passwordEncoder().encode("doo"),
                    new ArrayList<>(Arrays.asList(AuthTypeAuthor)));
            userRepo.save(u3);
            System.out.println(userRepo.findAll());
        };
    }

    @Bean
    CommandLineRunner init(IStorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
