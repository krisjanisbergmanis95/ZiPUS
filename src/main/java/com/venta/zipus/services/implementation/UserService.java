package com.venta.zipus.services.implementation;


import com.venta.zipus.models.user.User;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
