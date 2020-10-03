package com.venta.zipus.controllers.user;

import com.venta.zipus.models.user.User;
import com.venta.zipus.repositories.user.IUserRepo;
import com.venta.zipus.services.IUserService;
import com.venta.zipus.services.implementation.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    IUserRepo userRepo;

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
        System.out.println("CALLING REGISTER PAGE");
        logger.info("CALLING REGISTER PAGE");
        User user = new User();
        model.addAttribute("user", user);
        return "register-page";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult result) {
        System.out.println(user);
        logger.info("CALLING REGISTER PAGE NONONO");
        if (!result.hasErrors()) {
            userService.register(user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getPassword(), user.getAuthorities());
            return "redirect:/users";
        } else {
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
