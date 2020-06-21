package com.venta.ZiPUS.repositories.user;

import com.venta.ZiPUS.models.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IUserRepo extends CrudRepository<User, Long> {
    ArrayList<User> findAll();
    User findById(long id);
}
