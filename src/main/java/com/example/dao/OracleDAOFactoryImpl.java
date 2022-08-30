package com.example.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
public class OracleDAOFactoryImpl {

    //    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
//    private static final String DB_USERNAME = "ann";
//    private static final String DB_PASSWORD = "12345";
//    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static OracleDAOFactoryImpl instance;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER;
    private static Connection connection;

    private OracleDAOFactoryImpl() {
    }

    public static OracleDAOFactoryImpl getInstance() {
        if (instance == null) {
            return new OracleDAOFactoryImpl();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                System.out.println(DRIVER);
                System.out.println(DB_URL);
                System.out.println(DB_PASSWORD);
                System.out.println(DB_USERNAME);
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
