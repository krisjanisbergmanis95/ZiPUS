package com.venta.zipus.services.implementation;

import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.user.IUserAuthorityRepo;
import com.venta.zipus.services.IUserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthorityService implements IUserAuthorityService {
    @Autowired
    IUserAuthorityRepo userAuthorityRepo;

    @Override
    @Cacheable("userAuthority")
    public UserAuthority getUserAuthorityByTitle(String title) {
        System.out.println("Looking for role by title: " + title);
        System.out.println("Found: " + userAuthorityRepo.findByRoleTitle(title));
        return userAuthorityRepo.findByRoleTitle(title);
    }

    @Override
    @Cacheable("userAuthorities")
    public List<UserAuthority> getUserAuthorities() {
        return (List) userAuthorityRepo.findAll();
    }
}
