package com.bank.utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

public class DatabaseConnection {
    private static Connection conn;

    private DatabaseConnection() {
        
    }

    public static Connection getConnection(ServletContext context) throws SQLException, ClassNotFoundException {
    	  
        if (conn == null || conn.isClosed()) {
            String url = context.getInitParameter("DB_URL");
            String user = context.getInitParameter("DB_USER");
            String password = context.getInitParameter("DB_PASSWORD");
            String driver = context.getInitParameter("DB_DRIVER");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connection Established!");
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database Connection Closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
