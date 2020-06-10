package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String url = "jdbc:mysql://localhost:3306/mydbusers?useSSL=false";
    private final String user = "root";
    private final String password = "kbutyva1984";

    public Connection getConnection() {
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected database successfully...");
        } catch (SQLException throwables) {
            System.out.println("Connection database error...");
            throwables.printStackTrace();
        }
        return connection;
    }
}
