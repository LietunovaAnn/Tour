package com.example.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
//@PropertySource("classpath:app.properties")
public class OracleDAOFactoryImpl {

//    @Value("${spring.datasource.url}")
//    private static String DB_URL;
//    @Value("${spring.datasource.username}")
//    private static String DB_USERNAME;
//    @Value("${spring.datasource.password}")
//    private static String DB_PASSWORD;
//    @Value("${spring.datasource.driver-class-name}")
//    private static  String DRIVER;

    public static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    public static final String DB_USERNAME = "ann";
    public static final String DB_PASSWORD = "12345";
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static OracleDAOFactoryImpl instance;
    private static Connection connection;

    private OracleDAOFactoryImpl() {
    }

    public static OracleDAOFactoryImpl getInstance() {
        if (instance == null) {
            return new OracleDAOFactoryImpl();
        }
        return instance;
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                System.out.println(DRIVER);
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
