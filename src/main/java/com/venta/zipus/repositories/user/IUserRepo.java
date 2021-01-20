package com.venta.zipus.repositories.user;

import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;

public interface IUserRepo extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
    Collection<User> findByAuthoritiesIn(ArrayList<UserAuthority> authorityType);
    Collection<User> findAll();
    User findById(long id);
    boolean existsByEmail(String email);
}
