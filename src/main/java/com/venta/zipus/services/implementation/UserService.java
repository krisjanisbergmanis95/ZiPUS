package com.venta.zipus.services.implementation;

import com.venta.zipus.controllers.user.UserController;
import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.venta.zipus.WebSecurityConfig.passwordEncoder;

@Service
public class UserService implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserController.class);
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
    public User getUserByUsername(String username) {
        if (userRepo.count() > 0) {
            return userRepo.findByUsername(username);
        }
        return null;
    }

    @Override
    public boolean register(String name, String surname, String username, String email, String password, Collection<UserAuthority> authorities) {//name - as a username and unique
        if(userRepo.existsByUsername(username) || userRepo.existsByEmail(email))
        {
            return false;
        }
        User user = new User(name, surname, username, email, passwordEncoder().encode(password), (ArrayList<UserAuthority>) authorities);
        logger.info("service -");
        logger.info(user.toString());
        userRepo.save(user);
        logger.info(userRepo.findByUsername(username).toString());
        return true;
    }
}
