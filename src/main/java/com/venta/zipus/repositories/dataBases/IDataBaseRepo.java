package com.venta.zipus.repositories.dataBases;

import com.venta.zipus.models.databases.DataBase;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IDataBaseRepo extends CrudRepository<DataBase, Long> {
    ArrayList<DataBase> findAll();
    DataBase findByDataBaseName(String name);
}
