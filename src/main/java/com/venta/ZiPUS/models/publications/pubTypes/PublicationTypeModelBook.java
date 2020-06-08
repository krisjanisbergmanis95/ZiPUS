package com.venta.ZiPUS.models.publications.pubTypes;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity(name = "Book_Type")
@NoArgsConstructor
public class PublicationTypeModelBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_TYPE_ID")
    private long bookTypeId;

    @Column(name="Type_Field")
    public String publicationType;

    public PublicationTypeModelBook(String publicationType) {
        this.publicationType = publicationType;
    }
}
