package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    Util util = new Util();

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `user` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`));";

        try (Statement statement = util.getConnection().createStatement();) {

            statement.execute(sql);
            System.out.println("createUsersTable Execute SQL successfully");
        } catch (SQLException throwables) {
            System.out.println("ERROR createUsersTable");
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE user;";

        try (Statement statement = util.getConnection().createStatement();) {

            statement.execute(sql);
            System.out.println("dropUsersTable Execute SQL successfully");
        } catch (SQLException throwables) {
            System.out.println("ERROR dropUsersTable");
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        Session session = Util.getSessionFactory().openSession();

        // start the transaction
        Transaction transaction = session.beginTransaction();

        // save user object
        session.save(user);

        // commit transaction
        transaction.commit();

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction;

        // auto close session object
        Session session = Util.getSessionFactory().openSession();

        // start the transaction
        transaction = session.beginTransaction();

        // Id user object
        User user = session.get(User.class, id);
        session.delete(user);

        // commit transaction
        transaction.commit();

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction;
        List<User> users;
        // auto close session object
        Session session = Util.getSessionFactory().openSession();

        // start the transaction
        transaction = session.beginTransaction();

        // getAll object
        String sql = "select ID, NAME, lastname, age from USER";
        NativeQuery nativeQuery = session.createSQLQuery(sql).addEntity(User.class);

        users = nativeQuery.list();


        // commit transaction
        transaction.commit();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        List<User> users;
        // auto close session object
        Session session = Util.getSessionFactory().openSession();

        // start the transaction
        Transaction transaction = session.beginTransaction();

        // cleanAll object
        Criteria criteria = session.createCriteria(User.class);
        users = criteria.list();
        for (User user : users) {
            session.delete(user);
        }

        // commit transaction
        transaction.commit();

    }
}
