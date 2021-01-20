package com.venta.zipus.models.databases;

import com.venta.zipus.models.publications.Publication;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Data_Bases")
@NoArgsConstructor
public class DataBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DB_ID")
    private long dbID;

    @Column(name= "Data_Base_Name")
    private String dataBaseName;

    @ManyToMany(mappedBy = "dataBases")
    private Collection<Publication> publications;

    public DataBase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public Collection<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Collection<Publication> publications) {
        this.publications = publications;
    }
}
