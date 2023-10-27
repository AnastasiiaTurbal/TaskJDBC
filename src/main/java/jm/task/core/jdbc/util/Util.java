package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";
    private static final String URL = "jdbc:mysql://localhost:3306/task";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Statement statement;
    private static Session session;


    public static Statement getStatement() throws SQLException {
        if ((statement == null) || (statement.isClosed())) {
            try {
                Class.forName(DRIVER);
                statement = DriverManager.getConnection(URL, USER_NAME, PASSWORD).createStatement();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public static void closeStatement(Statement statement) throws SQLException {
        if (!(statement.isClosed())) {
            statement.getConnection().close();
        }
    }

    public static Session getSession() {
        if ((session == null) || (!(session.isOpen()))) {
            session = new Configuration()
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                    .setProperty("hibernate.connection.driver-class", DRIVER)
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USER_NAME)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", DIALECT)
                    .buildSessionFactory().openSession();
        }
        if (!(session.getTransaction().isActive())) {
            session.beginTransaction();
        }
        return session;
    }

    public static void closeSession(Session session) {
        if (session.isOpen()) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            session.getSessionFactory().close();
        }
    }

}
