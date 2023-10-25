package PP231.controller;

import PP231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import PP231.model.User;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController implements WebMvcConfigurer {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user-create";

    }

    @PostMapping()
    public String saveUser(@ModelAttribute(value="user") User user, Model model) {
        userService.addUser(user);
        List<User> users = userService.getAllUsers();
        model.addAttribute("user", users);
        return "redirect:/users";
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-create";
    }

    @RequestMapping("/delete_user")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
