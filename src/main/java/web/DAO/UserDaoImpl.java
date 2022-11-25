package web.DAO;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.SQLGrammarException;
import web.Util.Util;
import web.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    SessionFactory sf;
    Connection connection;
    public UserDaoImpl() {
        connection = Util.getConnection();
        sf = Util.getSessionFactory();
    }

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE `alegakom_basetest`.`users` (`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,`FirstName` VARCHAR(45) NULL,`LastName` VARCHAR(45) NULL,`Age` INT NULL, PRIMARY KEY ( `id` ));"); // создаю таблицу со значениями
            System.out.println("Список в базе данных создан");
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("Возможно таблица уже существует");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL ошибка");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement())  {
            statement.execute("DROP TABLE Users"); //удаляю таблицу по имени полностью из базы
            System.out.println("Список из удалён из базы данных");
        }
        catch (SQLSyntaxErrorException e) {
            System.out.println("Возможно таблица не существует");
        }catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    @Override
    public void saveUser(String name, String lastName, int age) {
        User user = new User(name, lastName, age);
        Session session = sf.openSession();
            try {
                // start a transaction
                session.beginTransaction();
                // save the user object
                session.save(user);
                // commit transaction
                session.getTransaction().commit();
                System.out.println("User с именем " + name + " добавлен в базу данных");
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                System.out.println("Ошибка запроса");
                e.printStackTrace();
            } finally {
                session.close();
            }
        }

    @Override
    public void removeUserById(long id) {
            Session session = sf.openSession();
            try {
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.delete(user);
                session.getTransaction().commit();
                System.out.println("Пользователь с id = " + id + " был удалён из базы");
            } catch (IllegalArgumentException e) {
                session.getTransaction().rollback();
                System.out.println("Возможно строки с таким id не существует");
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.out.println("Не получена транзакция");
                } else {
                    System.out.println("Ошибка запроса");
                }
            } finally {
                session.close();
            }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            list = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Ошибка запроса");
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Ошибка запроса");
        } finally {
            session.close();
        }
    }
}

