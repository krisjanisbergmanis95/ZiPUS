package com.venta.zipus.services;

import com.venta.zipus.models.user.UserAuthority;

import java.util.List;

public interface IUserAuthorityService {
    UserAuthority getUserAuthorityByTitle(String title);
    List<UserAuthority> getUserAuthorities();
}
