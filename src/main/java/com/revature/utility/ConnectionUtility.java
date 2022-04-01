package com.revature.utility;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        String db_url="jdbc:postgresql://35.239.200.190:5432/postgres";
        String db_userName=System.getenv("db_username");
        String db_password=System.getenv("db_password");
        Connection connection = DriverManager.getConnection(db_url,db_userName,db_password);
        return connection;//DriverManager.getConnection(db_url,db_userName,db_password);
    }
}
