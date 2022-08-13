package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDAOFactoryImpl implements DAOFactory {

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
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TourDAO getTourDAO() {
        return new TourDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAO();
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return new CustomerDAO();
    }

    @Override
    public ComplexityDAO getComplexityDAO() {
        return new ComplexityDAO();
    }

    @Override
    public DiscountDAO getDiscountDAO() {
        return new DiscountDAO();
    }

    @Override
    public TypeOfTourDAO getTypeOfTourDAO() {
        return new TypeOfTourDAO();
    }

    @Override
    public VariationDAO getVariationDAO() {
        return new VariationDAO();
    }

    @Override
    public void close() throws Exception {
        disconnect();
    }
}
