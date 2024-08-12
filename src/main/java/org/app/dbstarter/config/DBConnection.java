package org.app.dbstarter.config;

import java.sql.*;

public class DBConnection {
    public void createTable() {
        String sql_1 = "create table if not exists database_info(" +
                "id integer not null constraint database_info_pk primary key autoincrement," +
                "name TEXT not null unique," +
                "path TEXT)";
        String sql_2 = "INSERT OR IGNORE INTO database_info (name, path) VALUES ('POSTGRESQL', '')";
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql_1);
            statement.execute(sql_2);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:database.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void updatePostgreSQLPath(String path) {
        String sql = String.format("UPDATE database_info SET path = '%s' WHERE name = 'POSTGRESQL'", path);
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPostgreSQLPath() {
        String sql = "SELECT path FROM database_info WHERE name = 'POSTGRESQL'";
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            String path = resultSet.getString("path");
            statement.close();
            conn.close();
            return path;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
