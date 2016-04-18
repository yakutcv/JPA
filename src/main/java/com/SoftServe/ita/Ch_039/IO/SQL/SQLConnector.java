package com.SoftServe.ita.Ch_039.IO.SQL;

import java.sql.*;

/**
 * Created by ayasintc on 4/5/2016.
 */
public class SQLConnector {

    public static final String MySQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MySQL_URL = "jdbc:mysql://localhost/andrewdatabase";

    private String USER = "root";
    private String PASSWORD = "root";
    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws SQLException {
        if (connection != null) return;
        try{
            Class.forName(MySQL_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        connection = DriverManager.getConnection(MySQL_URL, USER, PASSWORD);
        //stmt = conn.createStatement();
        System.out.println("Connection is established");
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection close");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}







