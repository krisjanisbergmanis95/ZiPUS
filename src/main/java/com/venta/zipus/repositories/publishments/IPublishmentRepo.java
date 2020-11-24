package com.venta.zipus.repositories.publishments;

import com.venta.zipus.models.publishments.PublishmentType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface IPublishmentRepo extends PagingAndSortingRepository<PublishmentType, Long> {
    ArrayList<PublishmentType> findAll();
    PublishmentType findByPublishmentTypeName(String name);
}
