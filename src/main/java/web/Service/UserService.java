package web.Service;

import web.DAO.UserDao;
import web.models.User;

import java.util.List;

public interface UserService extends UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, int age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
