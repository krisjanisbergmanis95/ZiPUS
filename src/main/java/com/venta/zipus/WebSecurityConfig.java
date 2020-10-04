package com.venta.zipus;

import com.venta.zipus.services.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetailsServiceImpl myUserManager = new UserDetailsServiceImpl();
        return myUserManager;
    }

    @Override
    public void configure(HttpSecurity http) {
        try {
            http.authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
//                    .antMatchers("/h2-console/**").hasAuthority(ADMIN)
                    .anyRequest().authenticated() //need to authenticate on any request
                    .and()
                    .formLogin().permitAll() //login page is available for anyone
                    .loginPage("/login")
                    .and()
                    .logout().permitAll();//logout url link is available for anyone

            http.csrf().disable();
            http.headers().frameOptions().disable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
