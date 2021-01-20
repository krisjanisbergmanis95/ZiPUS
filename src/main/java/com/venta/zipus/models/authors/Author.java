package com.venta.zipus.models.authors;

import com.venta.zipus.models.publications.Publication;
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
    private long authorId;

    @Column(name= "Name")
    private String name;

    @Column(name= "Surname")
    private String surname;

    @Column(name="Works_In_VeA")
    private boolean workingInVea;

    @Column(name="Institution")
    private String institution;

    @ManyToMany(mappedBy = "authors")
    private Collection<Publication> publications;

    public Author(String name, String surname, boolean workingInVea) {
        this.name = name;
        this.surname = surname;
        this.workingInVea = workingInVea;
    }
    public Author(String name, String surname, boolean workingInVea, String institution) {
        this.name = name;
        this.surname = surname;
        this.workingInVea = workingInVea;
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", workingInVea=" + workingInVea +
                ", institution='" + institution + '\'' +
                ", publications=" + publications +
                '}';
    }
}
