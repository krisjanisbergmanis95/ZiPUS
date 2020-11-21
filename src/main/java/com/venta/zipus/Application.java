package com.venta.zipus;

import com.venta.zipus.config.WebSecurityConfig;
import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.databases.DataBase;
import com.venta.zipus.models.databases.constants.DataBaseNames;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.publications.PublicationBook;
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
import com.venta.zipus.repositories.IPublicationBookRepo;
import com.venta.zipus.repositories.authors.IAuthorRepo;
import com.venta.zipus.repositories.dataBases.IDataBaseRepo;
import com.venta.zipus.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.zipus.repositories.pubTypes.IPublicationTypeRepo;
import com.venta.zipus.repositories.publishments.IPublishmentRepo;
import com.venta.zipus.repositories.user.IUserAuthorityRepo;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IStorageService;
import com.venta.zipus.services.IUserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.venta.zipus.config.WebSecurityConfig.passwordEncoder;

@SpringBootApplication
//@EnableCaching
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    IPublicationTypeRepo publicationTypeRepo;

    @Autowired
    IPublicationTypeGroupsRepo publicationTypeGroupsRepo;

    @Autowired
    IDataBaseRepo dataBaseRepo;

    @Autowired
    IAuthorRepo authorRepo;

    @Autowired
    IPublicationBookRepo publicationBookRepo;

    @Autowired
    IPublishmentRepo publishmentRepo;

    @Autowired
    IUserAuthorityRepo userAuthorityRepo;

    @Autowired
    IUserService userService;

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
//            userRepo.save(u3);

//Adding a book
            PublicationType publicationType1 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesBook.EDUCATIONAL_BOOK);
            Author author1 = new Author("Juris", "Ābols", true, "VSRC");
            authorRepo.save(author1);

            ArrayList<Author> authors = new ArrayList<>(Arrays.asList(author1));


            ArrayList<String> keyWords = new ArrayList<>(Arrays.asList("java", "spring"));
            ArrayList<String> editors = new ArrayList<>(Arrays.asList("Jānis Stībelis", "Katrīna Pastarnaka"));
            //test pub type field holders
            PublicationBook pb1 = new PublicationBook("Izdota publikācija 1", editors, "Rīga");
            publicationBookRepo.save(pb1);
            PublishmentType publishType = publishmentRepo.findByPublishmentTypeName(PublishmentTypeNames.INTERNATIONAL_REVIEW);


            ArrayList<DataBase> dataBases1 = new ArrayList<>(
                    Arrays.asList(
                            dataBaseRepo.findByDataBaseName(DataBaseNames.EBSCO),
                            dataBaseRepo.findByDataBaseName(DataBaseNames.ENGINEERING_VILLAGE_2),
                            dataBaseRepo.findByDataBaseName(DataBaseNames.GOOGLE_SCHOLAR)
                    )
            );


//            new File("./upload-dir/" + "doa_stack.json");
//            MultipartFile mf = (MultipartFile) tf;
            File file = new File("./upload-dir/Visitor pattern.pdf");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", IOUtils.toByteArray(input));

            Publication p2 = new Publication(publicationType1,
                    "Latviešu",
                    "Pētījums par thymeleaf",
                    "Research about thymeleaf",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Astronomija",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "I231naKSDN",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb1,
                    "upload-dir/" + "Visitor pattern.pdf",
                    "Visitor pattern.pdf",
                    multipartFile.getBytes()
                    );
            //end of creating a book



            u3.addPublication(p2);
            userRepo.save(u3);
            System.out.println(userRepo.findAll());

            Collection<User> users = userRepo.findAll();
            logger.info(".... Fetching users");
            long start = System.currentTimeMillis();
//            users.forEach(user -> logger.info(Long.toString(user.getU_ID())));
            logger.info("user with id 26 -->" + userService.getUserById(26));
            logger.info("user with id 27 -->" + userService.getUserById(27));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));
            logger.info("user with id 30 -->" + userService.getUserById(30));

            logger.info("time " + (System.currentTimeMillis() - start));
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
