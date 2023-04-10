package org.example.repository;

import java.sql.*;

public class BaseRepository {


    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cats_food_shop",
                "postgres",
                "admin"
        );
    }

    protected Statement getStatement() throws SQLException {
        Connection con = getConnection();
        return con.createStatement();
    }


}
