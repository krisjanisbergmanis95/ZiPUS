package com.venta.ZiPUS.models.publications.pubTypes;

import com.venta.ZiPUS.models.publications.Publication;
import com.venta.ZiPUS.models.publications.pubTypeGroups.PublicationTypeGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Publication_Types")
@Getter
@Setter
@NoArgsConstructor
public class PublicationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBLICATION_TYPE_ID")
    private long pubTypeId;

    @Column(name = "Publication_Type_Value")
    private String publicationTypeValue;

    //    @ManyToMany(mappedBy = "pubTypes")
//    private Collection<Publication> publications;
    @OneToMany(mappedBy = "pubType")
    public Collection<Publication> publications;


    @ManyToOne
    @JoinColumn(name = "GROUP_TYPE_ID")
    public PublicationTypeGroup publicationGroup;

    public PublicationType(String publicationTypeValue, PublicationTypeGroup publicationGroup) {
        this.publicationTypeValue = publicationTypeValue;
        this.publicationGroup = publicationGroup;
    }

    @Override
    public String toString() {
        return "PublicationType{" +
                "bookTypeId=" + pubTypeId +
                ", publicationTypeValue='" + publicationTypeValue + '\'' +
//                ", publications=" + publications +
                ", publicationGroup=" + publicationGroup +
                '}';
    }
}
