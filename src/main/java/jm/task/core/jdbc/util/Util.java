package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";
    private static final String URL = "jdbc:mysql://localhost:3306/task";


    public static Connection getConnection() throws SQLException{

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);

    }

}
