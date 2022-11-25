package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Service.UserServiceImpl;
import web.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class UserController {
    User user = new User("Oleg", "Komissarov", 2);
//    User user1 = new User(2,30,"Alex");
//    User user2 = new User(3,42,"Kirill");
//    User user3 = new User(4,55,"Sergay");
//    User user4 = new User(5,2,"Bogdan");


    UserServiceImpl csi = new UserServiceImpl();


    @GetMapping(value = "/user")
    public String getUsers(@RequestParam(value = "count", defaultValue = "5") int count, ModelMap model){
        List<User> userList = new ArrayList<>();
        userList.add(user);
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//        userList.add(user4);
        model.addAttribute("userList", userList.stream().limit(count).collect(Collectors.toList()));
        // нужно не забыть указать передаваемый объект "userList" в .html
        return "user";
    }
}
