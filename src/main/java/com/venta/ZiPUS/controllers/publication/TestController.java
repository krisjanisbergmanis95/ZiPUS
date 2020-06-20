package com.venta.ZiPUS.controllers.publication;

import com.venta.ZiPUS.models.dataBases.DataBase;
import com.venta.ZiPUS.models.dataBases.constants.DataBaseNames;
import com.venta.ZiPUS.models.publications.Publication;
import com.venta.ZiPUS.models.publications.pubTypes.PublicationType;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesConferences;
import com.venta.ZiPUS.models.publications.pubTypes.constants.PublicationTypeTitlesMagazine;
import com.venta.ZiPUS.repositories.IPublicationRepo;
import com.venta.ZiPUS.repositories.dataBases.IDataBaseRepo;
import com.venta.ZiPUS.repositories.pubTypeGroups.IPublicationTypeGroupsRepo;
import com.venta.ZiPUS.repositories.pubTypes.IPublicationTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;

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
        PublicationType publicationType2 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesConferences.ARTICLE_IN_CONFERENCE_COLLECTION);
        PublicationType publicationType3 = publicationTypeRepo.findByPublicationTypeValue(PublicationTypeTitlesMagazine.PUB_IN_SC_MAGAZINE);

        ArrayList<DataBase> dataBases1 = new ArrayList<>(
                Arrays.asList(
                        dataBaseRepo.findByDataBaseName(DataBaseNames.EBSCO),
                        dataBaseRepo.findByDataBaseName(DataBaseNames.ENGINEERING_VILLAGE_2),
                        dataBaseRepo.findByDataBaseName(DataBaseNames.GOOGLE_SCHOLAR)
                )
        );

        Publication p1 = new Publication(publicationType2, "English");
        Publication p2 = new Publication(publicationType2, "Latviešu", "Pētījums par thymeleaf", dataBases1);

        publicationRepo.save(p1);
        publicationRepo.save(p2);


        return "hellopage";// this should show html "Hello my dude"
    }
}
