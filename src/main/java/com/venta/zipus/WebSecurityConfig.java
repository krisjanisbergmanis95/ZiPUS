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

//    @Bean
//    @Override
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user =
//                withDefaultPasswordEncoder().username("john").password("123").roles(USER).build();
//        UserDetails userAdmin =
//                withDefaultPasswordEncoder().username("admin").password("987").roles(ADMIN).build();
//
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(user, userAdmin);
//
//        return userDetailsManager;
//    }

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
                    .antMatchers("/product/showAllProducts").anonymous()
                    .antMatchers("/product/insertOneProduct").hasAuthority(ADMIN)
                    .antMatchers("/customer/showMyProducts/**").hasAnyAuthority(ADMIN, USER)
                    .antMatchers("/h2-console/**").hasAuthority(ADMIN)
//                    .antMatchers("/product/insertOneProduct").hasRole(ADMIN)
//                    .antMatchers("/customer/showMyProducts/**").hasAnyRole(ADMIN, USER)
//                    .antMatchers("/h2-console/**").hasRole(ADMIN)
                    .anyRequest().authenticated() //need to authenticate on any request
                    .and()
                    .formLogin().permitAll() //login page is available for anyone
                    .and()
                    .logout().permitAll();//logout url link is available for anyone

            http.csrf().disable();
            http.headers().frameOptions().disable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
