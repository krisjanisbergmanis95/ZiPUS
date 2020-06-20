package com.venta.ZiPUS.models.publications.pubTypeGroups;

import com.venta.ZiPUS.models.publications.Publication;
import com.venta.ZiPUS.models.publications.pubTypes.PublicationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Pub_type_groups")
@Getter
@Setter
@NoArgsConstructor
public class PublicationTypeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_TYPE_ID")
    private long groupTypeId;

    @Column(name = "Pup_Type_Group_Name")
    private String pugTypeGroupName;

    @OneToMany(mappedBy = "publicationGroup")
    private Collection<Publication> publications;

    @OneToMany(mappedBy = "publicationGroup")
    private Collection<PublicationType> publicationTypes;

    public PublicationTypeGroup(String pugTypeGroupName) {
        this.pugTypeGroupName = pugTypeGroupName;
    }

    @Override
    public String toString() {
        return "PublicationTypeGroup{" +
                "groupTypeId=" + groupTypeId +
                ", pugTypeGroupName='" + pugTypeGroupName + '\'' +
                '}';
    }
}
