package com.venta.zipus;

import com.venta.zipus.services.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) {
        try {
            http.authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/h2-console/**", "/users").hasAuthority(ADMIN)
                    .antMatchers("/home").hasAnyAuthority(USER, ADMIN)
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated() //need to authenticate on any request
                    .and()
                    .formLogin().permitAll() //login page is available for anyone
                    .loginPage("/login").permitAll()
                    .and()
                    .logout().permitAll();//logout url link is available for anyone

            http.csrf().disable();
            http.headers().frameOptions().disable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
