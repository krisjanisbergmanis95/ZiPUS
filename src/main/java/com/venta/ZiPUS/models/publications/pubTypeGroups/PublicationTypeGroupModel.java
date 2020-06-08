package com.venta.ZiPUS.models.publications.pubTypeGroups;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationTypeModel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Pub_type_groups")
@NoArgsConstructor
public class PublicationTypeGroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_GROUP_TYPE_ID")
    private long groupTypeId;

    @Column(name = "Pup_Type_Group_Name")
    private String pugTypeGroupName;

    @OneToMany(mappedBy = "publicationGroup")
    private Collection<PublicationTypeModel> publicationTypes;

    public PublicationTypeGroupModel(String pugTypeGroupName) {
        this.pugTypeGroupName = pugTypeGroupName;
    }
}
