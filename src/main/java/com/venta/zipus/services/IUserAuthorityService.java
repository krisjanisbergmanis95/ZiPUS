package com.venta.zipus.services;

import com.venta.zipus.models.user.UserAuthority;

public interface IUserAuthorityService {
    UserAuthority getUserAuthorityByTitle(String title);
}
