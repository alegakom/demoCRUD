package web.Service;


import web.DAO.UserDao;
import web.DAO.UserDaoImpl;
import web.models.User;

import java.util.List;

public class UserServiceImpl implements UserService {
   UserDao ud = new UserDaoImpl();
    @Override
    public void createUsersTable() {
        ud.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        ud.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, int age) {
        ud.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        ud.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return ud.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        ud.cleanUsersTable();
    }
}
