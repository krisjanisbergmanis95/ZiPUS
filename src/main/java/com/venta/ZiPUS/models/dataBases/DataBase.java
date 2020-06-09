package com.venta.ZiPUS.models.dataBases;

import com.venta.ZiPUS.models.publications.Publication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Table
@Entity(name = "Data_Bases")
@Getter
@Setter
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
}
