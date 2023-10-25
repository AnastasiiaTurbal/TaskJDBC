package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        final UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Anastasiia", "Degtyareva", (byte) 30);
        userService.saveUser("Ivan", "Ivanov", (byte) 55);
        userService.saveUser("Dan", "Cioca", (byte) 105);
        userService.saveUser("Petr", "Petrov", (byte) 20);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
