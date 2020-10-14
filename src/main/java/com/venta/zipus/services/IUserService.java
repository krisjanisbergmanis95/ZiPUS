package com.venta.zipus.services;


import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;

import java.util.Collection;

public interface IUserService {
    boolean register(String name, String surname, String username, String email, String password, Collection<UserAuthority> authorities);
    User getUserById(long id);

}
