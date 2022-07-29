package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static Connection conn;
    private static Util instance;

    private Util() {
        try {
            if (null == conn || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/userdb", "admin", "password");
                System.out.println("Connection to Users DB succesfull!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }
    public static Connection getConnection() {
        return conn;
    }
}
