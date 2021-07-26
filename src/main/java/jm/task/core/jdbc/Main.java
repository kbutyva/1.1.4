package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;


public class Main {
    public static void main(String[] args) {

        UserDao  userDao= new UserDaoJDBCImpl();
        UserDao userDao1 = new UserDaoHibernateImpl();
//        userDao.createUsersTable();
//        userDao.dropUsersTable();
//        userDao.saveUser("Aylana", "Kuular", (byte) 2);
        userDao1.removeUserById(1);
//        userDao.cleanUsersTable();
//        userDao.getAllUsers();
//        userDao.out.println(userDaoJDBC.getAllUsers());


    }
}
