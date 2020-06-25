package com.venta.ZiPUS.models.publications;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Table
@Entity(name = "Publication_Book_Table")
@Getter
@Setter
@NoArgsConstructor
public class PublicationBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUB_BOOK_ID")
    private long pub_book_ID;

    @OneToOne(mappedBy = "publicationBook")
    public Publication publication;

    @Column(name = "Name_Of_Book")
    private String nameOfBook;

    @Column(name = "Editors")
    private ArrayList<String> editors;

    @Column(name = "Place_Published")
    private String placePublished;

    public PublicationBook(String nameOfBook, ArrayList<String> editors, String placePublished) {
        this.nameOfBook = nameOfBook;
        this.editors = editors;
        this.placePublished = placePublished;
    }
}
