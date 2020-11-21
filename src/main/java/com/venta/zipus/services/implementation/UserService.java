package com.venta.zipus.services.implementation;

import com.venta.zipus.controllers.user.UserController;
import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.venta.zipus.config.WebSecurityConfig.passwordEncoder;

@Service
public class UserService implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserRepo userRepo;

    @Override
    @Cacheable(value = "users")
    public User getUserById(long id) {
//        try {
//            long time = 3000L;
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            throw new IllegalStateException(e);
//        }
        if (userRepo.count() > 0) {
            return userRepo.findById(id);
        }
        return null;
    }

    @Override
    @Cacheable(value = "users")
    public User getUserByUsername(String username) {
        if (userRepo.count() > 0) {
            return userRepo.findByUsername(username);
        }
        return null;
    }

    @Override
    public boolean register(String name, String surname, String username, String email, String password, Collection<UserAuthority> authorities) {//name - as a username and unique
        if (userRepo.existsByUsername(username) || userRepo.existsByEmail(email)) {
            return false;
        }
        User user = new User(name, surname, username, email, passwordEncoder().encode(password), (ArrayList<UserAuthority>) authorities);
        logger.info("service -");
        logger.info(user.toString());
        userRepo.save(user);
        logger.info(userRepo.findByUsername(username).toString());
        return true;
    }

    @Override
    @CachePut(value = "users")
    public boolean updateUser(User user) {
        if (userRepo.existsById(user.getU_ID()) && userRepo.existsByUsername(user.getUsername()) && userRepo.existsByEmail(user.getEmail())) {
            userRepo.save(user);
            return true;
        }
        return false;
    }
}
