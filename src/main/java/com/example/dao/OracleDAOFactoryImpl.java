package com.example.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
public class OracleDAOFactoryImpl {
    private static final Logger logger = LoggerFactory.getLogger(OracleDAOFactoryImpl.class);

    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER;
    @Value("${spring.datasource.script}")
    private String script;
    private static Connection connection;


    public Connection getConnection() {
        System.out.println("connection");
        if (connection == null) {
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @EventListener(classes = {ContextStartedEvent.class, ContextRefreshedEvent.class})
    public void DBInit() {
        try {

            System.out.println(getConnection().prepareStatement("SELECT * FROM CUSTOMERS ").executeQuery());
            logger.info("Database already exist");
        } catch (Exception e) {
            try {
                ScriptRunner sr = new ScriptRunner(getConnection());
                URL url = this.getClass().getClassLoader().getResource(script);
                sr.runScript(new BufferedReader(new FileReader(url.getFile())));
                logger.info("Database created");
            } catch (Exception ex) {
                System.err.println(ex);
                logger.warn("Database Creation failed");
            }
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
