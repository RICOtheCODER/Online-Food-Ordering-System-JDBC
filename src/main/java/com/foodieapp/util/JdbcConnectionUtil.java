package com.foodieapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionUtil {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/FoodDeliveryManagement";
        String username="root";
        String password="RicoSarkar@189";
        connection = DriverManager.getConnection(url,username,password);
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
