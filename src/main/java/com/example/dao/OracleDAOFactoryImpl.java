package com.example.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
public class OracleDAOFactoryImpl extends HikariDataSource {

    //    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
//    private static final String DB_USERNAME = "ann";
//    private static final String DB_PASSWORD = "12345";
//    private static final String DRIVER = "oracle.jdbc.OracleDriver";

    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER;
    private static Connection connection;

//    public OracleDAOFactoryImpl(DataSource dataSource) {
//        this.setDataSource(dataSource);
//    }

//    @Autowired
//    public OracleDAOFactoryImpl(@Value("${spring.datasource.driver-class-name}") String driver,
//                                @Value("${spring.datasource.url}") String url,
//                                @Value("${spring.datasource.username}") String username,
//                                @Value("${spring.datasource.password}") String password) {
//        this.setMaximumPoolSize(20);
//        this.setDriverClassName(driver);
//        this.setJdbcUrl(url);
//        this.setUsername(username);
//        this.setPassword(password);
//    }


    public Connection getConnection() {
        System.out.println("connection");
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
