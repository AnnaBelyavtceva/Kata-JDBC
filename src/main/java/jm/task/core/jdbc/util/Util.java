package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/schema_user";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка соединения" + e);
        }
        return conn;
    }

}
