package com.venta.ZiPUS.models.publications;

import com.venta.ZiPUS.models.publications.pubTypes.*;
import lombok.*;
import org.apache.tomcat.jni.Error;

import javax.persistence.*;
import java.util.ArrayList;

@Table
@Entity(name = "Publication_Table")
@Getter
@Setter
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUB_ID")
    private long pub_ID;

    @Column(name = "PUB_TYPE")
    private String pubType;

    @Column(name = "PUB_TYPE_GROUP")
    private String pubTypeGroup;

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

    public Publication(String pubType, String language) {

    }


    @Override
    public String toString() {
        return "Publication{" +
                "pub_ID=" + pub_ID +
                ", pubType='" + pubType + '\'' +
                ", pubTypeGroup='" + pubTypeGroup + '\'' +
                ", language='" + language + '\'' +
                ", scDataBases=" + scDataBases +
                ", nameOfBook='" + nameOfBook + '\'' +
                ", nameOfMagazine='" + nameOfMagazine + '\'' +
                ", nameOfCollection='" + nameOfCollection + '\'' +
                '}';
    }

    private String getPublicationGroup(String pubType) throws Exception {
        String groupName = null;
        switch (pubType) {
            case "Mācību grāmata":
            case "Publikācija zinātnisko rakstu krājumā":
            case "Nodaļa zinātniskā monogrāfijā":
            case "Zinātniskā monogrāfija":
                groupName = "book";
                break;
            case "Publikācija zinātniskos žurnālos":
                groupName = "journal";
                break;
            case "Raksts pilna teksta konferenču raksta krājumā":
            case "Raksts konferenču tēžu krājumā":
                groupName = "conference";
                break;
        }
        if (groupName == null) {
            throw new Exception("Illegal publication type");
        }
        return groupName;
    }

    public void setPublicationGroupByType() throws Exception {
        try {
            pubTypeGroup = getPublicationGroup(pubType);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
