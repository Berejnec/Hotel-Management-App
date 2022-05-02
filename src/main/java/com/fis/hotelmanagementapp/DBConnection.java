package com.fis.hotelmanagementapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public Connection databaseLink;

    private String url = "jdbc:mysql://localhost:3306/hotel-database";

    private String username = "root";

    private String password = "administrator";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }

}
