package com.venta.zipus.repositories.publishments;

import com.venta.zipus.models.publishments.PublishmentType;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPublishmentRepo extends CrudRepository<PublishmentType, Long> {
    ArrayList<PublishmentType> findAll();
    PublishmentType findByPublishmentTypeName(String name);
}
