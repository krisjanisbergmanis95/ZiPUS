package com.venta.zipus;

import com.venta.zipus.config.WebSecurityConfig;
import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.databases.DataBase;
import com.venta.zipus.models.databases.constants.DataBaseNames;
import com.venta.zipus.models.publications.Publication;
import com.venta.zipus.models.publications.PublicationBook;
import com.venta.zipus.models.publications.PublicationConference;
import com.venta.zipus.models.publications.PublicationMagazine;
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
import com.venta.zipus.repositories.IPublicationConferenceRepo;
import com.venta.zipus.repositories.IPublicationMagazineRepo;
import com.venta.zipus.repositories.IPublicationRepo;
import com.venta.zipus.repositories.authors.IAuthorRepo;
import com.venta.zipus.repositories.dataBases.IDataBaseRepo;
import com.venta.zipus.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.zipus.repositories.pubTypes.IPublicationTypeRepo;
import com.venta.zipus.repositories.publishments.IPublishmentRepo;
import com.venta.zipus.repositories.user.IUserAuthorityRepo;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IPublicationTypeService;
import com.venta.zipus.services.IUserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static com.venta.zipus.config.WebSecurityConfig.passwordEncoder;

@SpringBootApplication
@EnableCaching
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
    IPublicationMagazineRepo publicationMagazineRepoRepo;
    @Autowired
    IPublicationConferenceRepo publicationConferenceRepo;

    @Autowired
    IPublicationRepo publicationRepo;

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
            User u2 = new User("Ešs", "Kečums", "admin", "labaikais@pokemon.com", passwordEncoder().encode("admin"), new ArrayList<>(Arrays.asList(AuthTypeAdmin)));
            userRepo.save(u2);

            UserAuthority AuthTypeAuthor = userAuthorityRepo.findByRoleTitle(WebSecurityConfig.AUTHOR);
            User u3 = new User("john",
                    "doe",
                    "doo",
                    "doe@dank.com",
                    passwordEncoder().encode("doo"),
                    new ArrayList<>(Arrays.asList(AuthTypeAuthor)));
            userRepo.save(u3);

            UserAuthority AuthTypeZUADD = userAuthorityRepo.findByRoleTitle(WebSecurityConfig.ZUADD);
            User u4 = new User(
                    "zane",
                    "zane",
                    "zane",
                    "zane@zane.com",
                    passwordEncoder().encode("zane"),
                    new ArrayList<>(Arrays.asList(AuthTypeZUADD))
            );
            userRepo.save(u4);

//Adding a book

            PublicationType publicationType1 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesBook.EDUCATIONAL_BOOK);
            PublicationType publicationType2 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesBook.PUB_IN_SC_PAPER_COLLECTION);
            PublicationType publicationType3 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesBook.SC_MONOGRAPH);
            PublicationType publicationType4 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesMagazine.PUB_IN_SC_MAGAZINE);
            PublicationType publicationType5= publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesConferences.ARTICLE_IN_CONFERENCE_COLLECTION);
            PublicationType publicationType6= publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesConferences.ARTICLE_IN_THESIS_COLLECTION);
            Author author1 = new Author("Juris", "Ābols", true, "VSRC");
            authorRepo.save(author1);

            ArrayList<Author> authors = new ArrayList<>(Arrays.asList(author1));


            ArrayList<String> keyWords = new ArrayList<>(Arrays.asList("java", "spring"));
            ArrayList<String> editors = new ArrayList<>(Arrays.asList("Jānis Stībelis", "Katrīna Pastarnaka"));
            //test pub type field holders
            PublicationBook pb1 = new PublicationBook("Izdota publikācija 1", editors, "Rīga");
            PublicationBook pb2 = new PublicationBook("Izdota publikācija 2", editors, "Liepāja");
            PublicationBook pb3 = new PublicationBook("Izdota publikācija 3", editors, "Ventspils");
            PublicationBook pb4 = new PublicationBook("Izdota publikācija 4", editors, "Daugavpils");
            PublicationBook pb5 = new PublicationBook("Izdota publikācija 5", editors, "Jelgava");
            PublicationBook pb6 = new PublicationBook("Izdota publikācija 6", editors, "Rīga");
            PublicationBook pb7 = new PublicationBook("Izdota publikācija 7", editors, "Roja");
            PublicationBook pb8 = new PublicationBook("Izdota publikācija 8", editors, "Kuldīga");
            PublicationBook pb9 = new PublicationBook("Izdota publikācija 9", editors, "Valmiera");
            PublicationConference pc10 = new PublicationConference("Izdota publikācija 10", new Date(), "Latvija", "Rīga", "Publickāciju kolekcija1", "Kabile", "33");
            PublicationConference pc11 = new PublicationConference("Izdota publikācija 11", new Date(), "Latvija", "Liepāja", "Publickāciju kolekcija2", "Sabile", "1");
            PublicationMagazine pm12 = new PublicationMagazine("Izdota publikācija 12", editors, "123321K");
            publicationBookRepo.save(pb1);
            publicationBookRepo.save(pb2);
            publicationBookRepo.save(pb3);
            publicationBookRepo.save(pb4);
            publicationBookRepo.save(pb5);
            publicationBookRepo.save(pb6);
            publicationBookRepo.save(pb7);
            publicationBookRepo.save(pb8);
            publicationBookRepo.save(pb9);
            publicationConferenceRepo.save(pc10);
            publicationConferenceRepo.save(pc11);
            publicationMagazineRepoRepo.save(pm12);

            ArrayList<DataBase> dataBases1 = new ArrayList<>(
                    Arrays.asList(
                            dataBaseRepo.findByDataBaseName(DataBaseNames.EBSCO),
                            dataBaseRepo.findByDataBaseName(DataBaseNames.ENGINEERING_VILLAGE_2),
                            dataBaseRepo.findByDataBaseName(DataBaseNames.GOOGLE_SCHOLAR)
                    )
            );

            try {
                logger.info("copying test file");
                File file1 = new File("./src/main/resources/Visitor_pattern.pdf");
                File file2 = new File("./upload-dir/Visitor_pattern.pdf");
                Files.copy(file1.toPath(), file2.toPath());
                logger.info("File copied");
            } catch (IOException e) {
                logger.error("Error while copying test file");
                logger.error(e.getMessage());
            }

            File file = new File("./upload-dir/Visitor_pattern.pdf");
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
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes(),
                    new ArrayList<User>(Arrays.asList(u3))
            );

            Publication p3 = new Publication(publicationType1,
                    "Latviešu",
                    "Aplikācijas tests A",
                    "app test A",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Elektromagnētisms",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "AA2da23SDNadsads",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb2,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes(),
                    new ArrayList<User>(Arrays.asList(u3))
            );

            Publication p4 = new Publication(publicationType2,
                    "Latviešu",
                    "Aplikācijas tests B",
                    "app test B",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Big data",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "BA2123QQ",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb3,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );

            Publication p5 = new Publication(publicationType3,
                    "Latviešu",
                    "CAplikācijas tests 1",
                    "Capp test 1",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Inženierija",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "33BA2123QQ",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb4,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );


            Publication p6 = new Publication(publicationType3,
                    "Latviešu",
                    "CAplikācijas tests 2",
                    "Capp test 2",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Zemniecība",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "33BA2123QQ4",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb5,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );
            Publication p7 = new Publication(publicationType1,
                    "Latviešu",
                    "DDDDD test",
                    "DDD test app",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Kvantu teorija",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "DDDDDD123",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb6,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );

            Publication p8 = new Publication(publicationType1,
                    "Latviešu",
                    "DDDDD test 2",
                    "DDD test app 2",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Mehānika",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "DDDDDD123123",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb7,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );

            Publication p9 = new Publication(publicationType3,
                    "Latviešu",
                    "CCCCCCCCCCCCCCCCC",
                    "App ccccc",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Astronomija",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "CICICINN31",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pb8,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );

            Publication p10 = new Publication(publicationType6,
                    "Latviešu",
                    "TEST pub 10",
                    "Test pub 10",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Bioloģija",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "TESTPUB10",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pc10,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );
            Publication p11 = new Publication(publicationType5,
                    "Grieķu",
                    "TEST pub 11",
                    "Test pub 11",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Matemātika",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "TESTPUB11",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pc11,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );

            Publication p12 = new Publication(publicationType4,
                    "Latviešu",
                    "TEST pub 12",
                    "Test publication 12",
                    "Šeit ir anotācija",
                    "This is anotation",
                    "Doatorzinātnes",
                    authors,
                    keyWords,
                    "Zvaigzne ABC",
                    2004,
                    460,
                    "TESTPUB12",
//                    publishType,
//                    dataBases1,
                    "https://www.google.com",
                    "Just additional notes",
                    pm12,
                    "upload-dir/" + "Visitor_pattern.pdf",
                    "Visitor_pattern.pdf",
                    multipartFile.getBytes()
            );

            publicationRepo.save(p2);
            publicationRepo.save(p3);
            publicationRepo.save(p4);
            publicationRepo.save(p5);
            publicationRepo.save(p6);
            publicationRepo.save(p7);
            publicationRepo.save(p8);
            publicationRepo.save(p9);
            publicationRepo.save(p10);
            publicationRepo.save(p11);
            publicationRepo.save(p12);


            //end of creating a book


//            u3.addPublication(p2);

            System.out.println(userRepo.findAll());

            Collection<User> users = userRepo.findAll();
            logger.info(".... Fetching users");
            long start = System.currentTimeMillis();
            users.forEach(user -> logger.info(Long.toString(user.getU_ID())));
            logger.info("user with id 26 -->" + userService.getUserById(26));
            logger.info("user with id 27 -->" + userService.getUserById(27));
            logger.info("user with id 28 -->" + userService.getUserById(28));
            logger.info("time " + (System.currentTimeMillis() - start));
        };


    }
}
