package com.venta.ZiPUS.models.publications;

import com.venta.ZiPUS.models.Authors.Author;
import com.venta.ZiPUS.models.publications.pubTypeGroups.PublicationTypeGroup;
import com.venta.ZiPUS.models.publications.pubTypes.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Pievienot jaunu one-to-one nulable ar katru grupas veidu, un būs klāt tikai tie lauki kas nav null.
 * ja magazine ==null tad neko nerādīs
 */
@Table
@Entity(name = "Publication_Table")
@Getter
@Setter
public class Publication {
//    @Autowired
//    IPublicationTypeGroupsRepo publicationTypeGroupsRepo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUB_ID")
    private long pub_ID;

    @ManyToOne
    @JoinColumn(name = "GROUP_TYPE_ID")
    private PublicationTypeGroup publicationGroup;

//    @ManyToMany
//    @JoinTable(name = "Pub_Type_Pub",
//            joinColumns = @JoinColumn(name = "PUB_TYPE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PUB_ID"))
//    private Collection<PublicationType> pubTypes;

    @ManyToOne
    @JoinColumn(name = "Pub_Type")
    private PublicationType pubType;

    @ManyToMany
    @JoinTable(name = "Data_Base_Pub",
            joinColumns = @JoinColumn(name = "DB_ID"),
            inverseJoinColumns = @JoinColumn(name = "PUB_ID"))
    private Collection<PublicationType> dataBases;

    @ManyToMany
    @JoinTable(name = "Publishment_Publication",
            joinColumns = @JoinColumn(name = "PUBISHMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PUB_ID"))
    private Collection<PublicationType> publishments;

    @Column(name = "Language")
    private String language;

    @Column(name = "Publication_Title_Origin")
    private String publicationTitleOrigin;

    @Column(name = "Publication_Title_English")
    private String publicationTitleEnglish;

    @Column(name = "Annotation")
    private String annotation;

    @Column(name = "Annotation_English")
    private String annotationEnglish;

    @Column(name = "Field_Of_Research")
    private String fieldOfResearch;

    //TODO Autora modelis kur ir vārds uzvārds darba vieta + papildus info par autoru
//    @Column(name="Authors")
//    private ArrayList<String> authors;
    @ManyToMany
    @JoinTable(name = "Author_Publication",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "PUB_ID"))
    private Collection<Author> authors;

    @Column(name = "Key_Words")
    private ArrayList<String> keyWords;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "Published_Year")
    private Date publishedYear;

    @Column(name = "Pages")
    private int pages;

    @Column(name = "ISBN_ISSN")
    private String isbnISSN;

    //TODO OtherDB

    @Column(name = "HyperLink")
    private String HyperLink;

    @Column(name = "Notes")
    private String notes;

    //TODO FILE TO IMPORT

//    @Column(name = "Name_Of_Book")
//    private String nameOfBook;
//
//    @Column(name = "Place_Published")
//    private String placePublished;

//    @Column(name = "Name_Of_Magazine")
//    private String nameOfMagazine;
//
//    @Column(name = "Editors")
//    private ArrayList<String> editors;
//
//    @Column(name = "Serial_Number")
//    private String serialNumber;
//
//    @Column(name = "Name_Of_Collection")
//    private String nameOfCollection;

//    @Column(name = "Date")
//    private Date date;
//
//    @Column(name = "Volume")//Sējums
//    private String volume;
//
//    @Column(name = "Country")
//    private String country;
//
//    @Column(name = "City")
//    private String city;
//
//    @Column(name = "Conference_Name")
//    private String conferenceName;



    public Publication() {
    }

    public Publication(PublicationType pubType, String language) {
        this.pubType = pubType;
        this.publicationGroup = pubType.getPublicationGroup();
        this.language = language;
    }

    public Publication(PublicationType pubType, String language, String publicationTitleOrigin) {
        this.pubType = pubType;
        this.publicationGroup = pubType.getPublicationGroup();
        this.language = language;
        this.publicationTitleOrigin = publicationTitleOrigin;
    }

    public PublicationType getPubType() {
        return pubType;
    }

    public String getPublicationTitleOrigin() {
        return publicationTitleOrigin;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "pub_ID=" + pub_ID +
                ", pubType='" + pubType + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
