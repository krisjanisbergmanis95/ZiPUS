package com.venta.zipus.services.implementation;

import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.user.IUserAuthorityRepo;
import com.venta.zipus.services.IUserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthorityService implements IUserAuthorityService {
    @Autowired
    IUserAuthorityRepo userAuthorityRepo;

    @Override
    public UserAuthority getUserAuthorityByTitle(String title) {
        return userAuthorityRepo.findByRoleTitle(title);
    }

    @Override
    public List<UserAuthority> getUserAuthorities() {
        return (List) userAuthorityRepo.findAll();
    }
}
