package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public String createUser(@RequestParam(value = "firstname") String firstName,
                             @RequestParam(value = "lastname") String lastName,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "age") Integer age,
                             ModelMap model) {
        userService.createUser(new User(firstName, lastName, email, age));
        model.addAttribute("firstname", firstName);
        model.addAttribute("lastname", lastName);
        return "usercreated";
    }

    @GetMapping(value = "/get")
    public String getUserById(@RequestParam(value = "id") Integer id, ModelMap model) {
        User user = userService.getUser(id);
        if (user == null) {
            return "notfound";
        }
        model.addAttribute("user", user);
        return "userinfo";
    }

    @GetMapping(value = "/getall")
    public String getUsers(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "allusersinfo";
    }

    @GetMapping(value = "/")
    public String home() {
        return "forward:/getall";
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestParam(value = "id") Integer id,
                             @RequestParam(value = "firstname") String firstName,
                             @RequestParam(value = "lastname") String lastName,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "age") Integer age) {
        User user = userService.getUser(id);
        if (user == null) {
            return "notfound";
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAge(age);
        userService.updateUser(user);
        return "userupdated";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        User user = userService.getUser(id);
        if (user == null) {
            return "notfound";
        }
        userService.deleteUser(user);
        return "userdeleted";
    }
}
