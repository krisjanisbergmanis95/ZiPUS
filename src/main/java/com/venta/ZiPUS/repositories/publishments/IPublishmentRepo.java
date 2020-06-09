package com.venta.ZiPUS.repositories.publishments;

import com.venta.ZiPUS.models.publishments.PublishmentType;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublishmentRepo extends CrudRepository<PublishmentType, Long> {
    ArrayList<PublishmentType> findAll();
}
