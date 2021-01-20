package com.venta.zipus.services.implementation;


import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.repositories.user.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepo userRepo;

    @Override
    @Cacheable("userDetailsRepo")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepo.existsByUsername(username)) {
            return new MyUserDetails(userRepo.findByUsername(username));
        }
        throw new UsernameNotFoundException("User '" + username + "' not found!");
    }
}
