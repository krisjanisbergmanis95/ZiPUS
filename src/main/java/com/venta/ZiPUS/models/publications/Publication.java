package com.venta.ZiPUS.models.publications;

import com.venta.ZiPUS.models.publications.pubTypes.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Table
@Entity(name = "Publication_Table")
@Getter
@Setter
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUB_ID")
    private long pub_ID;

    @ManyToOne
    @JoinColumn(name="PUB_TYPE_ID")
    private PublicationType pubType;

//    @Column(name = "PUB_TYPE_GROUP")
//    private String pubTypeGroup;

    @Column(name = "Language")
    private String language;

    @Column(name = "Sc_Data_Bases")
    private ArrayList<String> scDataBases;

    @Column(name = "Name_Of_Book")
    private String nameOfBook;

    @Column(name = "Name_Of_Magazine")
    private String nameOfMagazine;

    @Column(name = "Name_Of_Collection")
    private String nameOfCollection;

    public Publication() {
//        this.pubType = pub
    }

    public Publication(PublicationType pubType, String language) {

    }

    @Override
    public String toString() {
        return "Publication{" +
                "pub_ID=" + pub_ID +
                ", pubType='" + pubType + '\'' +
//                ", pubTypeGroup='" + pubTypeGroup + '\'' +
                ", language='" + language + '\'' +
                ", scDataBases=" + scDataBases +
                ", nameOfBook='" + nameOfBook + '\'' +
                ", nameOfMagazine='" + nameOfMagazine + '\'' +
                ", nameOfCollection='" + nameOfCollection + '\'' +
                '}';
    }

    private String getPublicationGroup(PublicationType pubType) throws Exception {
        String groupName = "";
//        switch (pubType) {
//            case "Mācību grāmata":
//            case "Publikācija zinātnisko rakstu krājumā":
//            case "Nodaļa zinātniskā monogrāfijā":
//            case "Zinātniskā monogrāfija":
//                groupName = "book";
//                break;
//            case "Publikācija zinātniskos žurnālos":
//                groupName = "journal";
//                break;
//            case "Raksts pilna teksta konferenču raksta krājumā":
//            case "Raksts konferenču tēžu krājumā":
//                groupName = "conference";
//                break;
//        }
//        if (groupName == null) {
//            throw new Exception("Illegal publication type");
//        }
        return groupName;
    }

    public void setPublicationGroupByType() throws Exception {
        try {
//            pubTypeGroup = getPublicationGroup(pubType);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
