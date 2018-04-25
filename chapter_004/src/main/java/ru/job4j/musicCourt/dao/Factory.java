package ru.job4j.musicCourt.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Factory {

    private static final Logger Log = LoggerFactory.getLogger(Factory.class);

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/musicCourt";
    private static final String username = "postgres";
    private static final String password = "root";
    private static final int CONN_POOL_SIZE = 5;

    private BasicDataSource bds;

    private Connection connection;

    public Factory() {
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

    private static final Factory instance = new Factory();

    public static Factory getInstance() {
        return instance;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public Connection getConnection() throws SQLException {
        return connection = bds.getConnection();
    }


}
