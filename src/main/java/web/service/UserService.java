package web.service;

import web.dao.UserDao;
import web.model.User;

import java.util.List;

public interface UserService extends UserDao {
    List<User>getAllUsers ();
    void saveUser(User user);
    void removeUser(long id);
    void updateUser(User user);
    User getUserById(long id);
}
