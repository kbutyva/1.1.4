package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

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
            Criteria criteria = session.createCriteria(User.class);
            users = criteria.list();

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
