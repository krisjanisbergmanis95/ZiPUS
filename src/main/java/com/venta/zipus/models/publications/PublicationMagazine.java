package com.venta.zipus.models.publications;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Table
@Entity(name = "Publication_Magazine_Table")
@Getter
@Setter
@NoArgsConstructor
public class PublicationMagazine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUB_MAGAZINE_ID")
    private long pub_magazine_ID;

    @OneToOne(mappedBy = "publicationMagazine")
    public Publication publication;

    @Column(name = "Name_Of_Magazine")
    private String nameOfMagazine;

    @Column(name = "Editors")
    private ArrayList<String> editors;

    @Column(name = "Serial_Number")
    private String serialNumber;

    public PublicationMagazine(String nameOfMagazine, ArrayList<String> editors, String serialNumber) {
        this.nameOfMagazine = nameOfMagazine;
        this.editors = editors;
        this.serialNumber = serialNumber;
    }
}
