package com.venta.ZiPUS.repositories.dataBases;

import com.venta.ZiPUS.models.dataBases.DataBase;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IDataBaseRepo extends CrudRepository<DataBase, Long> {
    ArrayList<DataBase> findAll();
    DataBase findByDataBaseName(String name);
}
