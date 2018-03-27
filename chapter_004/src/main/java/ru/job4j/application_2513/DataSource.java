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

    private BasicDataSource bds;

    private Connection connection;

    public DataSource() {
        this.bds = new BasicDataSource();
        bds.setDriverClassName(DRIVER_CLASS_NAME);
        bds.setUrl(url);
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setInitialSize(CONN_POOL_SIZE);
        bds.setMaxTotal(100);
        bds.setMaxWaitMillis(10000);
        bds.setMaxIdle(10);
    }

    private static final DataSource instance = new DataSource();

    public static DataSource getInstance() {
        return instance;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public Connection getConnection() throws SQLException {
        return connection = bds.getConnection();
    }

}
