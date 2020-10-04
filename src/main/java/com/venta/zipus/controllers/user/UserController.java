package com.venta.zipus.controllers.user;

import com.venta.zipus.models.user.User;
import com.venta.zipus.models.user.UserAuthority;
import com.venta.zipus.repositories.user.IUserRepo;
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

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private static ArrayList<User> users = new ArrayList<>();
    @Autowired
    IUserService userService;
    @Autowired
    IUserAuthorityService userAuthorityService;

    @GetMapping("/users")
    public String userspage(Model model) {
        System.out.println("Working?");

//        User u1 = new User("liga", "ozola", "lozol", "lg@gmail.com", "parole123", "student");
//        users.add(u1);
//
//        System.out.println(users.size());


//        model.addAttribute("user", users);

        return "userpage";

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
            return "redirect:/login";
        } else {
            logger.info("Something wrong?");
            logger.info(result.toString());
            return "redirect:/register-page";
        }
    }


    @GetMapping("/users/{id}")
    public String showProductById(@PathVariable(name = "id") int id, Model model) {
        if (id >= 0 && id < users.size()) {
            model.addAttribute("variableUser", users.get(id));
            return "user-profile";
        } else {
            return "error";
        }
    }
}
