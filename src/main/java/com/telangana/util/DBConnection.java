package com.telangana.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://centerbeam.proxy.rlwy.net:45065/railway"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "ZmrTARijRwfNprATyhfXVYIPrWYCOXOF";

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("✅ DB Connected Successfully");

        } 
        catch (Exception e) {

            System.out.println("❌ Database Connection Error: " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}