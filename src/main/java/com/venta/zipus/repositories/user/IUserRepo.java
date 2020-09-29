package com.venta.zipus.repositories.user;

import com.venta.zipus.models.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IUserRepo extends CrudRepository<User, Long> {
    ArrayList<User> findAll();
    User findById(long id);
}
