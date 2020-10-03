package com.venta.zipus.services.implementation;


import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepo userRepo;

    @Override
    public User getUserById(long id) {
        if (userRepo.count() > 0) {
            return userRepo.findById(id);
        }
        return null;
    }

    @Override
    public boolean register(String name, String surname, String username, String email, String password, Collection<UserAuthority> authorities) {//name - as a username and unique
        if(userRepo.existsByUsername(username) || userRepo.existsByEmail(email))
        {
            return false;
        }

        userRepo.save(new User(name, surname, username, email, password, (ArrayList<UserAuthority>) authorities));
        return true;
    }
}
