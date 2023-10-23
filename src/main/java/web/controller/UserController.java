package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;


@RequestMapping("/")
@Controller
public class UserController implements WebMvcConfigurer {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "all_users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user_create";

    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute(value="user") User user, Model model) {
        userService.addUser(user);
        List<User> users = userService.getAllUsers();
        model.addAttribute("user", users);
        return "redirect:/all_users";
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_create";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/all_users";
    }

}
