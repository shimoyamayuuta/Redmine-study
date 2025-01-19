package com.example.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

	public static final String DB_URL = "jdbc:postgresql://localhost:5432/Redmine";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "admin";

    // DB接続を確立するメソッド
    public Connection getConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}