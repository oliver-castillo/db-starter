package org.app.dbstarter.config;

import org.app.dbstarter.service.PostgreSQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DBConnection {
    private final Logger logger = LoggerFactory.getLogger(PostgreSQLService.class);

    public Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:database.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }
        return null;
    }
}
