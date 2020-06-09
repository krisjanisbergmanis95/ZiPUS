package com.venta.ZiPUS.models.publications;

import com.venta.ZiPUS.models.publications.pubTypes.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Table
@Entity(name = "Publication_Table")
@Getter
@Setter
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUB_ID")
    private long pub_ID;

    @ManyToMany
    @JoinTable(name = "Pub_Type_Pub",
            joinColumns = @JoinColumn(name = "PUB_TYPE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PUB_ID"))
    private Collection<PublicationType> pubTypes;

    @ManyToMany
    @JoinTable(name = "Data_Base_Pub",
            joinColumns = @JoinColumn(name = "DB_ID"),
            inverseJoinColumns = @JoinColumn(name = "PUB_ID"))
    private Collection<PublicationType> dataBases;

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
    }

    public Publication(PublicationType pubType, String language) {

    }

    @Override
    public String toString() {
        return "Publication{" +
                "pub_ID=" + pub_ID +
                ", pubType='" + pubTypes + '\'' +
                ", language='" + language + '\'' +
                ", scDataBases=" + scDataBases +
                ", nameOfBook='" + nameOfBook + '\'' +
                ", nameOfMagazine='" + nameOfMagazine + '\'' +
                ", nameOfCollection='" + nameOfCollection + '\'' +
                '}';
    }
}
