package com.venta.zipus.models.publications;

import com.venta.zipus.models.authors.Author;
import com.venta.zipus.models.databases.DataBase;
import com.venta.zipus.models.publications.pubtypegroups.PublicationTypeGroup;
import com.venta.zipus.models.publications.pubtypes.*;
import com.venta.zipus.models.publishments.PublishmentType;
import com.venta.zipus.models.user.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Pievienot jaunu one-to-one nulable ar katru grupas veidu, un būs klāt tikai tie lauki kas nav null.
 * ja magazine ==null tad neko nerādīs
 */
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
    private Collection<DataBase> dataBases;

//    @ManyToMany
//    @JoinTable(name = "Publishment_Publication",
//            joinColumns = @JoinColumn(name = "PUBISHMENT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PUB_ID"))
//    private Collection<PublicationType> publishments;

    @ManyToMany(mappedBy = "publications")
    private Collection<User> users;

    @ManyToOne
    @JoinColumn(name = "PUBLISHMENT_ID")
    private PublishmentType publishment;

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
    private int publishedYear;

    @Column(name = "Pages")
    private int pages;

    @Column(name = "ISBN_ISSN")
    private String isbnISSN;

    //TODO OtherDB

    @Column(name = "HyperLink")
    private String hyperLink;

    @Column(name = "Notes")
    private String notes;

    //TODO FILE TO IMPORT
    @Column(name = "filePath")
    private String filePath;

    @Column(name = "fileName")
    private String fileName;

    @OneToOne
    @JoinColumn(name = "PUB_BOOK_ID")
    private PublicationBook publicationBook;

    @OneToOne
    @JoinColumn(name = "PUB_MAGAZINE_ID")
    private PublicationMagazine publicationMagazine;

    @OneToOne
    @JoinColumn(name = "PUB_SC_ID")
    private PublicationConference publicationConference;

    public Publication(PublicationType pubType, String language) {//TODO REMOVE THIS CONSTRUCTOR
        this.pubType = pubType;
        this.publicationGroup = pubType.getPublicationGroup();
        this.language = language;
    }

    public Publication(
            PublicationType pubType,
            String language,
            String publicationTitleOrigin,
            String publicationTitleEnglish,
            String annotation,
            String annotationEnglish,
            String fieldOfResearch,
            ArrayList<Author> authors,
            ArrayList<String> keyWords,
            String publisher,
            int publishedYear,
            int pages,
            String isbnISSN,
//            PublishmentType publishment,
//            ArrayList<DataBase> dataBases,
            String hyperLink,
            String notes,
            PublicationBook publicationBook
    ) {
        this.pubType = pubType;
//        this.publicationGroup = pubType.getPublicationGroup();
        this.language = language;
        this.publicationTitleOrigin = publicationTitleOrigin;
        this.publicationTitleEnglish = publicationTitleEnglish;
        this.annotation = annotation;
        this.annotationEnglish = annotationEnglish;
        this.fieldOfResearch = fieldOfResearch;
        this.authors = authors;
        this.keyWords = keyWords;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.pages = pages;
        this.isbnISSN = isbnISSN;
//        this.publishment = publishment;
//        this.dataBases = dataBases;
        //TODO cita datu bāze
        this.hyperLink = hyperLink;
        this.notes = notes;
        this.publicationBook = publicationBook;
    }

    public Publication(
            PublicationType pubType,
            String language,
            String publicationTitleOrigin,
            String publicationTitleEnglish,
            String annotation,
            String annotationEnglish,
            String fieldOfResearch,
            ArrayList<Author> authors,
            ArrayList<String> keyWords,
            String publisher,
            int publishedYear,
            int pages,
            String isbnISSN,
//            PublishmentType publishment,
//            ArrayList<DataBase> dataBases,
            String hyperLink,
            String notes,
            PublicationBook publicationBook,
            String filePath,
            String fileName
    ) {
        this.pubType = pubType;
//        this.publicationGroup = pubType.getPublicationGroup();
        this.language = language;
        this.publicationTitleOrigin = publicationTitleOrigin;
        this.publicationTitleEnglish = publicationTitleEnglish;
        this.annotation = annotation;
        this.annotationEnglish = annotationEnglish;
        this.fieldOfResearch = fieldOfResearch;
        this.authors = authors;
        this.keyWords = keyWords;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.pages = pages;
        this.isbnISSN = isbnISSN;
//        this.publishment = publishment;
//        this.dataBases = dataBases;
        //TODO cita datu bāze
        this.hyperLink = hyperLink;
        this.notes = notes;
        this.publicationBook = publicationBook;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public Publication(PublicationType pubType,
                       String language,
                       String publicationTitleOrigin,
                       String publicationTitleEnglish,
                       String annotation,
                       String annotationEnglish,
                       String fieldOfResearch,
                       ArrayList<Author> authors,
                       ArrayList<String> keyWords,
                       String publisher,
                       int publishedYear,
                       int pages,
                       String isbnISSN,
//                       PublishmentType publishment,
//                       ArrayList<DataBase> dataBases,
                       String hyperLink,
                       String notes,
                       PublicationMagazine publicationMagazine
    ) {
        this.pubType = pubType;
//        this.publicationGroup = pubType.getPublicationGroup();
        this.language = language;
        this.publicationTitleOrigin = publicationTitleOrigin;
        this.publicationTitleEnglish = publicationTitleEnglish;
        this.annotation = annotation;
        this.annotationEnglish = annotationEnglish;
        this.fieldOfResearch = fieldOfResearch;
        this.authors = authors;
        this.keyWords = keyWords;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.pages = pages;
        this.isbnISSN = isbnISSN;
//        this.publishment = publishment;
//        this.dataBases = dataBases;
        //TODO cita datu bāze
        this.hyperLink = hyperLink;
        this.notes = notes;
        this.publicationMagazine = publicationMagazine;
    }

    public Publication(PublicationType pubType,
                       String language,
                       String publicationTitleOrigin,
                       String publicationTitleEnglish,
                       String annotation,
                       String annotationEnglish,
                       String fieldOfResearch,
                       ArrayList<Author> authors,
                       ArrayList<String> keyWords,
                       String publisher,
                       int publishedYear,
                       int pages,
                       String isbnISSN,
//                       PublishmentType publishment,
//                       ArrayList<DataBase> dataBases,
                       String hyperLink,
                       String notes,
                       PublicationConference publicationConference
    ) {
        this.pubType = pubType;
//        this.publicationGroup = pubType.getPublicationGroup();
        this.language = language;
        this.publicationTitleOrigin = publicationTitleOrigin;
        this.publicationTitleEnglish = publicationTitleEnglish;
        this.annotation = annotation;
        this.annotationEnglish = annotationEnglish;
        this.fieldOfResearch = fieldOfResearch;
        this.authors = authors;
        this.keyWords = keyWords;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.pages = pages;
        this.isbnISSN = isbnISSN;
//        this.publishment = publishment;
//        this.dataBases = dataBases;
        //TODO cita datu bāze
        this.hyperLink = hyperLink;
        this.notes = notes;
        this.publicationConference = publicationConference;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "pub_ID=" + pub_ID +
                ", pubType='" + pubType + '\'' +
                ", language='" + language + '\'' +
                ", filepath = " + filePath + '\'' +
                '}';
    }
}
