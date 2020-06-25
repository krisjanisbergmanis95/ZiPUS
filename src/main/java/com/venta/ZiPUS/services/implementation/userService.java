package com.venta.ZiPUS.services.implementation;


import com.venta.ZiPUS.models.User.User;
import com.venta.ZiPUS.repositories.user.IUserRepo;
import com.venta.ZiPUS.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService implements IUserService {

    @Autowired
    IUserRepo userRepo;

    @Override
    public User getUserById(long id) {
        if (userRepo.count() > 0) {
            return userRepo.findById(id);
        }
        return null;
    }
}
