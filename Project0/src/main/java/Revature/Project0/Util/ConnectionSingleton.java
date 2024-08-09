package Revature.Project0.Util;

import java.sql.*;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Properties;

public class ConnectionSingleton {
    private static Connection conn;
    private static final String url = "jdbc:postgresql://localhost:5432/project0database";
    private static final String dbPassword = System.getenv("Password");


    private static Properties props = new Properties();
    static{
        props.setProperty("user", "mikel");
        props.setProperty("password", dbPassword);
        props.setProperty("currentSchema","public");
    }

    private ConnectionSingleton()  {

    }

    public synchronized static Connection getConnection() throws SQLException{
        if (conn == null || conn.isClosed()) {

            try {
                conn = DriverManager.getConnection(url, props);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;

    }

    public static void main(String[] args) {

    }

}
