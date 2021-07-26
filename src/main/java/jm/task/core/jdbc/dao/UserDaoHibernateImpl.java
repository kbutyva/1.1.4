package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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
        Transaction transaction = null;

        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // save user object
            session.save(user);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Hibernate saveUser Error");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // Id user object
            User user = (User) session.get(User.class, id);
            session.delete(user);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Hibernate removeUserById Error");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // getAll object
            Criteria criteria = session.createCriteria(User.class);
            users = criteria.list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Hibernate getAllUsers Error");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        List<User> users;
        // auto close session object
        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // cleanAll object
            Criteria criteria = session.createCriteria(User.class);
            users = criteria.list();
            for (User user : users) {
                session.delete(user);
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
