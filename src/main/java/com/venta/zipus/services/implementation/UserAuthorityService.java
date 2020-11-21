package com.venta.zipus.services.implementation;

import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.user.IUserAuthorityRepo;
import com.venta.zipus.services.IUserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorityService implements IUserAuthorityService {
    @Autowired
    IUserAuthorityRepo userAuthorityRepo;
    @Override
    @Cacheable("userAuthority")
    public UserAuthority getUserAuthorityByTitle(String title) {
        return userAuthorityRepo.findByRoleTitle(title);
    }
}
