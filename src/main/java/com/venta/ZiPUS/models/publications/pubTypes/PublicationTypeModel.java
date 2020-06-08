package com.venta.ZiPUS.models.publications.pubTypes;
import com.venta.ZiPUS.models.publications.pubTypeGroups.PublicationTypeGroupModel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity(name = "Publication_Types")
@NoArgsConstructor
public class PublicationTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBLICATION_TYPE_ID")
    private long bookTypeId;

    @Column(name= "Publication_Type")
    private String publicationType;

    @ManyToOne
    @JoinColumn(name="BOOK_GROUP_TYPE_ID")
    public PublicationTypeGroupModel publicationGroup;

    public PublicationTypeModel(String publicationType, PublicationTypeGroupModel publicationGroup) {
        this.publicationType = publicationType;
        this.publicationGroup = publicationGroup;
    }
}
