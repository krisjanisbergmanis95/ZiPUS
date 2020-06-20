package com.venta.ZiPUS.controllers.publication;

import com.venta.ZiPUS.models.Authors.Author;
import com.venta.ZiPUS.models.dataBases.DataBase;
import com.venta.ZiPUS.models.dataBases.constants.DataBaseNames;
import com.venta.ZiPUS.models.publications.Publication;
import com.venta.ZiPUS.models.publications.PublicationBook;
import com.venta.ZiPUS.models.publications.PublicationConference;
import com.venta.ZiPUS.models.publications.PublicationMagazine;
import com.venta.ZiPUS.models.publications.pubTypes.PublicationType;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesBook;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesConferences;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesMagazine;
import com.venta.ZiPUS.models.publishments.PublishmentType;
import com.venta.ZiPUS.models.publishments.constants.PublishmentTypeNames;
import com.venta.ZiPUS.repositories.IPublicationBookRepo;
import com.venta.ZiPUS.repositories.IPublicationConferenceRepo;
import com.venta.ZiPUS.repositories.IPublicationMagazineRepo;
import com.venta.ZiPUS.repositories.IPublicationRepo;
import com.venta.ZiPUS.repositories.authors.IAuthorRepo;
import com.venta.ZiPUS.repositories.dataBases.IDataBaseRepo;
import com.venta.ZiPUS.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.ZiPUS.repositories.pubTypes.IPublicationTypeRepo;
import com.venta.ZiPUS.repositories.publishments.IPublishmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    IPublicationTypeRepo publicationTypeRepo;

    @Autowired
    IPublicationTypeGroupsRepo PublicationTypeGroupsRepo;

    @Autowired
    IPublicationRepo publicationRepo;
    @Autowired
    IDataBaseRepo dataBaseRepo;
    @Autowired
    IPublishmentRepo publishmentRepo;
    @Autowired
    IAuthorRepo authorRepo;

    @Autowired
    IPublicationBookRepo publicationBookRepo;
    @Autowired
    IPublicationMagazineRepo publicationMagazineRepo;
    @Autowired
    IPublicationConferenceRepo publicationConferenceRepo;


    @GetMapping("/publications/types-to-groups") // endpoint for localhost:8080
    public String showHelloPage() {
        System.out.println("=========");
        PublicationType publicationType = publicationTypeRepo.findByPublicationTypeValue("Mācību grāmata");
        System.out.println(publicationType);
        System.out.println("----------");
        System.out.println(
                PublicationTypeGroupsRepo.findByPublicationTypesIn(
                        new ArrayList<>(Arrays.asList(publicationType))
                )
        );
        System.out.println("=========");
        PublicationType publicationType2 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesConferences.ARTICLE_IN_CONFERENCE_COLLECTION);
        System.out.println(publicationType2);
        System.out.println("----------");
        System.out.println(
                PublicationTypeGroupsRepo.findByPublicationTypesIn(
                        new ArrayList<>(Arrays.asList(publicationType2))
                )
        );
        System.out.println("=========");
        System.out.println("=========");
        PublicationType publicationType3 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesMagazine.PUB_IN_SC_MAGAZINE);
        System.out.println(publicationType3);
        System.out.println("----------");
        System.out.println(
                PublicationTypeGroupsRepo.findByPublicationTypesIn(
                        new ArrayList<>(Arrays.asList(publicationType3))
                )
        );
        System.out.println("=========");
//        System.out.println(p1.toString());
        return "hellopage";// this should show html "Hello my dude"
    }

    @GetMapping("/publications/add-pubs") // endpoint for localhost:8080
    public String createTestPubs() {
        PublicationType publicationType1 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesBook.EDUCATIONAL_BOOK);
        PublicationType publicationType2 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesConferences.ARTICLE_IN_CONFERENCE_COLLECTION);
        PublicationType publicationType3 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesMagazine.PUB_IN_SC_MAGAZINE);

        ArrayList<DataBase> dataBases1 = new ArrayList<>(
                Arrays.asList(
                        dataBaseRepo.findByDataBaseName(DataBaseNames.EBSCO),
                        dataBaseRepo.findByDataBaseName(DataBaseNames.ENGINEERING_VILLAGE_2),
                        dataBaseRepo.findByDataBaseName(DataBaseNames.GOOGLE_SCHOLAR)
                )
        );

        PublishmentType publishType = publishmentRepo.findByPublishmentTypeName(PublishmentTypeNames.INTERNATIONAL_REVIEW);
        Author author1 = new Author("Juris", "Ābols", true, "VSRC");
        authorRepo.save(author1);

        ArrayList<Author> authors = new ArrayList<>(Arrays.asList(author1));


        ArrayList<String> keyWords = new ArrayList<>(Arrays.asList("java", "spring"));
        ArrayList<String> editors = new ArrayList<>(Arrays.asList("Jānis Stībelis", "Katrīna Pastarnaka"));
        //test pub type field holders
        PublicationBook pb1 = new PublicationBook("Izdota publikācija 1", editors, "Rīga");
        PublicationMagazine pm1 = new PublicationMagazine("Ilustrētā zinātne", editors, "sn32141");
        PublicationConference pc1 = new PublicationConference("Ikgadējā zinātniskā konference",
                new Date(), "Latvija", "Ventspils", "Publikāciju krājums N1",
                "Rīga", "4");

        publicationBookRepo.save(pb1);
        publicationMagazineRepo.save(pm1);
        publicationConferenceRepo.save(pc1);

        //test pubs
        Publication p1 = new Publication(publicationType2, "English");
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
                publishType,
                dataBases1,
                "https://www.google.com",
                "Just additional notes",
                pb1);

        Publication p3 = new Publication(publicationType3,
                "Latviešu",
                "Pētījums par thymeleaf žurnālam",
                "Research about thymeleaf to journal",
                "Šeit ir anotācija",
                "This is anotation",
                "Astronomija",
                authors,
                keyWords,
                "izdevniecība 44",
                2015,
                41,
                "I2KadsSDN",
                publishType,
                dataBases1,
                "https://www.google.com",
                "Just additional notes",
                pm1);

        Publication p4 = new Publication(publicationType3,
                "Latviešu",
                "Pētījums par thymeleaf konforencē",
                "Research about thymeleaf conforence",
                "Šeit ir anotācija",
                "This is anotation conforence",
                "Astronomija",
                authors,
                keyWords,
                "Jānis roze",
                2020,
                420,
                "I231na123N",
                publishType,
                dataBases1,
                "https://www.google.com",
                "Just additional notasddaslmdlasmldsadsaes",
                pc1);

        publicationRepo.save(p1);
        publicationRepo.save(p2);
        publicationRepo.save(p3);
        publicationRepo.save(p4);


        return "hellopage";// this should show html "Hello my dude"
    }
}
