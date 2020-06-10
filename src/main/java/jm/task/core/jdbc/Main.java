package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;


public class Main {
    public static void main(String[] args) {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
  //     userDaoJDBC.createUsersTable();
 //     userDaoJDBC.dropUsersTable();
       userDaoJDBC.saveUser("Aylana", "Kuular", (byte) 2);
//       userDaoJDBC.removeUserById(100);
//      userDaoJDBC.cleanUsersTable();
        userDaoJDBC.getAllUsers();
        System.out.println(userDaoJDBC.getAllUsers());



    }
}
