package com.venta.zipus.repositories.user;

import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;

public interface IUserAuthorityRepo extends CrudRepository<UserAuthority, Integer> {
    Collection<UserAuthority> findByUsersIn(ArrayList<User> user);
    Collection<UserAuthority> findAll();
    UserAuthority findByRoleTitle(String title);
}
