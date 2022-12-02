package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDaoImpl;
import web.service.UserService;
import web.service.UserServiceImpl;
import web.model.User;

import javax.persistence.EntityManager;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired()
    public UserController(UserService us) {
        this.userService = us;
    }

    @GetMapping(value = "/user")
    public String getUsers(@RequestParam(value = "count", defaultValue = "100") int count, ModelMap model){
        List<User> userList;
        userList = userService.getAllUsers();
        model.addAttribute("userList",
                userList.stream().limit(count).toList());
        // нужно не забыть указать передаваемый объект "userList" в .html
        return "user";
    }
    @PostMapping(value = "/user/save")
    public String saveUser(@RequestParam(value = "name") String name,
                           @RequestParam(value = "lastName") String lastName,
                           @RequestParam(value = "age") int age){
        User user = new User(name, lastName, age);
        userService.saveUser(user);
        return "redirect: /user";
    }
    @PostMapping("/user/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect: /user";
    }

    @GetMapping(value = "user/update/{id}")
    public String update(@PathVariable(value = "id") int id){
        return "update";
    }
    @PostMapping(value ="user/update/{id}")
    public String updateUserById(@PathVariable(value = "id") int id,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "lastName") String lastName,
                                 @RequestParam(value = "age") int age){
        User user = userService.getUserById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userService.updateUser(user);
        return "redirect: /user";
    }
}
