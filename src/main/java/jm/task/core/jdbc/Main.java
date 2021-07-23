package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;


public class Main {
    public static void main(String[] args) {

        UserDao  userDao= new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//        userDao.dropUsersTable();
//        userDao.saveUser("Aylana", "Kuular", (byte) 2);
//        userDao.removeUserById(100);
        userDao.cleanUsersTable();
//        userDao.getAllUsers();
//        userDao.out.println(userDaoJDBC.getAllUsers());


    }
}
