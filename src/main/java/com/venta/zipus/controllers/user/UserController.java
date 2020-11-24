package com.venta.zipus.controllers.user;

import com.venta.zipus.config.WebSecurityConfig;
import com.venta.zipus.models.user.User;
import com.venta.zipus.services.IUserAuthorityService;
import com.venta.zipus.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;

import static com.venta.zipus.helpers.UserHelper.getCurrentUsername;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private static ArrayList<User> users = new ArrayList<>();
    @Autowired
    IUserService userService;
    @Autowired
    IUserAuthorityService userAuthorityService;

    @GetMapping("/users")
    public String showAllUsersPage(Model model) {
        User user = userService.getUserByUsername(getCurrentUsername());
        logger.info(user.toString());

        Boolean isAdmin = user.isAuthority(
                userAuthorityService.getUserAuthorityByTitle(WebSecurityConfig.ADMIN));
        logger.info("is admin = " + isAdmin);
        logger.info(user.getAuthorities().toString());
        logger.info(userAuthorityService.getUserAuthorityByTitle(WebSecurityConfig.ADMIN).getRoleTitle());
        if (isAdmin) {
            model.addAttribute("isAuthorityAdmin", true);
            logger.info("loading user list");
            model.addAttribute("users", userService.getAllUsers());
            return "userpage";
        }
        return "error";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        logger.info("CALLING REGISTER GET");
        User user = new User();
        model.addAttribute("user", user);
        return "register-page";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult result) {
        logger.info("CALLING REGISTER POST");
        logger.info(user.toString());
        logger.info(user.getRegisterWithAuth());
        try {
            user.addAuthority(userAuthorityService.getUserAuthorityByTitle(user.getRegisterWithAuth()));
        } catch (Exception e) {
            logger.info("what");
            logger.info(e.getMessage());
            logger.info("what");
        }
        logger.info(user.toString());
        if (!result.hasErrors()) {
            userService.register(user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getPassword(), user.getAuthorities());
            logger.info("Registered successfully redirecting to login page");
            return "redirect:/login";
        } else {
            logger.info("Something wrong?");
            logger.info(result.toString());
            return "redirect:/register-page";
        }
    }


    @GetMapping("/users/{id}")
    public String showProductById(@PathVariable(name = "id") int id, Model model) {
        if (id > 0) {
            logger.info("Loading user page for user with id:" + id);
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
            return "user-profile";
        } else {
            logger.error("error for id " + id);
            return "error";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
