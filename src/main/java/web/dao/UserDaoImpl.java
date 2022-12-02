package web.dao;
import java.util.List;
import org.springframework.stereotype.Component;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getAllUsers() {
        String sql = "select u from User u";
        return entityManager.createQuery(sql, User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(long id) {

        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class,id);
    }
}

