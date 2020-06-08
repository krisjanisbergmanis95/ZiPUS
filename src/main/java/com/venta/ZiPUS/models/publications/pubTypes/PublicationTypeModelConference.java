package com.venta.ZiPUS.models.publications.pubTypes;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity(name = "Conference_Type")
@NoArgsConstructor
public class PublicationTypeModelConference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONFERENCE_TYPE_ID")
    private long conferenceTypeId;

    @Column(name="Type_Field")
    public String publicationType;

    public PublicationTypeModelConference(String publicationType) {
        this.publicationType = publicationType;
    }
}
