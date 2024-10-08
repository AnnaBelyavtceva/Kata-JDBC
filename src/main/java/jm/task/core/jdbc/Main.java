package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Yura","Kot", (byte) 4);
        userService.saveUser("Pupa","Kot", (byte) 3);
        userService.saveUser("Lupa","Kot", (byte) 3);
        userService.saveUser("Aksel","Pyos", (byte) 5);

        System.out.println();

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
