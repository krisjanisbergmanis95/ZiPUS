package com.venta.ZiPUS.models.publications.pubTypeGroups;

import com.venta.ZiPUS.models.publications.pubTypes.PublicationType;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Pub_type_groups")
@NoArgsConstructor
public class PublicationTypeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_GROUP_TYPE_ID")
    private long groupTypeId;

    @Column(name = "Pup_Type_Group_Name")
    private String pugTypeGroupName;

    @OneToMany(mappedBy = "publicationGroup")
    private Collection<PublicationType> publicationTypes;

    public PublicationTypeGroup(String pugTypeGroupName) {
        this.pugTypeGroupName = pugTypeGroupName;
    }
}
