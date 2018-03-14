package ru.job4j.application_2513;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
    private static final String username = "postgres";
    private static final String password = "root";
    private static final int CONN_POOL_SIZE = 5;

    private BasicDataSource bds = new BasicDataSource();

    public DataSource() {
        bds.setDriverClassName(DRIVER_CLASS_NAME);
        bds.setUrl(url);
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setInitialSize(CONN_POOL_SIZE);
    }

    private static final DataSource instance = new DataSource();

    public static DataSource getInstance() {
        return instance;
    }

    public BasicDataSource getBds() {
        return bds;
    }

    public Connection getConnection() {
        try {
            return bds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
