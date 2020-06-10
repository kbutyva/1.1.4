package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Util util = new Util();

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS `users` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`));";

        try (Statement statement = util.getConnection().createStatement();) {

            statement.execute(sql);
            System.out.println("createUsersTable Execute SQL successfully");
        } catch (SQLException throwables) {
            System.out.println("ERROR createUsersTable");
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        try (Statement statement = util.getConnection().createStatement();) {

            statement.execute(sql);
            System.out.println("dropUsersTable Execute SQL successfully");
        } catch (SQLException throwables) {
            System.out.println("ERROR dropUsersTable");
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users VALUES (id,'" + name + "', '" + lastName + "', " + age + ");";
        try (Statement statement = util.getConnection().createStatement();) {

            statement.executeUpdate(sql);
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException throwables) {
            System.out.println("ERROR saveUser");
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = " + id + ";";
        try (Statement statement = util.getConnection().createStatement();) {

            statement.execute(sql);

            System.out.println("removeUserById Execute SQL successfully");
        } catch (SQLException throwables) {
            System.out.println("ERROR removeUserById");
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users;";
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try (Statement statement = util.getConnection().createStatement();) {

            resultSet = statement.executeQuery(sql);
            System.out.println("getAllUsers Execute SQL successfully");


            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getNString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            System.out.println("ERROR getAllUsers");
            throwables.printStackTrace();
        }


        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users;";
        try (Statement statement = util.getConnection().createStatement();) {

            statement.execute(sql);
            System.out.println("cleanUsersTable Execute SQL successfully");
        } catch (SQLException throwables) {
            System.out.println("ERROR cleanUsersTable");
            throwables.printStackTrace();
        }
    }
}
