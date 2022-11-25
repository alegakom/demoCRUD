package web;
import web.Service.UserService;
import web.Service.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
//        us.createUsersTable();
//        us.cleanUsersTable();
//        us.dropUsersTable();
//        us.removeUserById(2);
//        us.getAllUsers();
        us.saveUser("Oleg", "Komissarov", 29);
    }
}
