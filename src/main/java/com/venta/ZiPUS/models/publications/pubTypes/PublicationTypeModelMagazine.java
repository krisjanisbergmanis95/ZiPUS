package com.venta.ZiPUS.models.publications.pubTypes;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity(name = "Magazine_Type")
@NoArgsConstructor
public class PublicationTypeModelMagazine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAGAZINE_TYPE_ID")
    private long magazineTypeId;

    @Column(name = "Type_Field")
    public String publicationType;

    public PublicationTypeModelMagazine(String publicationType) {
        this.publicationType = publicationType;
    }
}
