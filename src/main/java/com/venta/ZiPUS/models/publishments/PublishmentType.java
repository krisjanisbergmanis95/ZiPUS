package com.venta.ZiPUS.models.publishments;

import com.venta.ZiPUS.models.publications.Publication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Publishment")
@Getter
@Setter
@NoArgsConstructor
public class PublishmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBLISHMENT_ID")
    private long dbID;

    @Column(name= "Publishment_Type_Name")
    private String publishmentTypeName;

    @OneToMany(mappedBy = "publishment")
    private Collection<Publication> publications;

    public PublishmentType(String publishmentTypeName) {
        this.publishmentTypeName = publishmentTypeName;
    }
}
