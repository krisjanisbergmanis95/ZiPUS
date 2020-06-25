package com.venta.ZiPUS.models.publications;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Table
@Entity(name = "Publication_Conference_Table")
@Getter
@Setter
@NoArgsConstructor
public class PublicationConference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUB_SC_ID")
    private long pub_sc_ID;

    @OneToOne(mappedBy = "publicationConference")
    public Publication publication;

    @Column(name = "Conference_Name")
    private String conferenceName;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Country")
    private String country;

    @Column(name = "City")
    private String city;

    @Column(name = "Name_Of_Collection")
    private String nameOfCollection;

    @Column(name = "Place_Published")
    private String placePublished;

    @Column(name = "Volume")//Sējums
    private String volume;

    public PublicationConference(String conferenceName,
                                 Date date,
                                 String country,
                                 String city,
                                 String nameOfCollection,
                                 String placePublished,
                                 String volume) {
        this.conferenceName = conferenceName;
        this.date = date;
        this.country = country;
        this.city = city;
        this.nameOfCollection = nameOfCollection;
        this.placePublished = placePublished;
        this.volume = volume;

    }

}
