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

   public void createUsersTable() {

       try (Statement statement = Util.getConnection().createStatement()){
          statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (" +
                   "id bigint auto_increment primary key, " +
                   "name varchar(30) not null," +
                   "lastName varchar(30) not null, " +
                   "age tinyint not null)");
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "INSERT INTO Users (name, lastName, age) value (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sqlQuery)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем '" + name + "' добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlQuery = "DELETE FROM Users WHERE id=?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sqlQuery)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList();

        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate("DELETE FROM Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
