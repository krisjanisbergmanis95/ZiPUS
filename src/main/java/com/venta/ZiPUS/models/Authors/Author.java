package com.venta.ZiPUS.models.Authors;

import com.venta.ZiPUS.models.publications.Publication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Author")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID")
    private long dbID;

    @Column(name= "Name")
    private String name;

    @Column(name= "Surname")
    private String surname;

    @Column(name="Works_In_VeA")
    private boolean worksInVea;

    @Column(name="Institution")
    private String institution;

    @ManyToMany(mappedBy = "authors")
    private Collection<Publication> publications;

    public Author(String name, String surname, boolean worksInVea) {
        this.name = name;
        this.surname = surname;
        this.worksInVea = worksInVea;
    }
    public Author(String name, String surname, boolean worksInVea, String institution) {
        this.name = name;
        this.surname = surname;
        this.worksInVea = worksInVea;
        this.institution = institution;
    }
}
