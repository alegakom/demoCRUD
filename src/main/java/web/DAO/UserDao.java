package web.DAO;

import web.models.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, int age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
