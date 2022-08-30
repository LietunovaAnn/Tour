package com.example.dao;

import com.example.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final static Connection connection = OracleDAOFactoryImpl.getConnection();
    private static OrderDAO instance;
    private OrderDAO() {
    }
    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public List<Order> showAllOrder() {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ORDERS");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                orderList.add(parseOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    public boolean addOrder(Order order) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO ORDERS(ORDERS_ID, TOUR_ID, CUSTOMERS_ID, DISCOUNT_PRICE) VALUES (?, ?, ?, ?) ")) {

            preparedStatement.setInt(1, getNextVal());
            preparedStatement.setInt(2, order.getTourId());
            preparedStatement.setInt(3, order.getCustomerId());
            preparedStatement.setInt(4, order.getDiscountPrise());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private int getNextVal() {
        ResultSet resultSet = null;
        try {
            resultSet = connection
                    .createStatement().executeQuery("SELECT ORDERS_SEQ.nextval from ORDERS");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrder(int id) {
        ResultSet resultSet = null;
        Order order = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM ORDERS WHERE ORDERS_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = parseOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return order;
    }

    public int getCustomerIdByOrderId(int id) {
        ResultSet resultSet = null;
        Order order = null;
        int customerId = 0;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT CUSTOMERS_ID FROM ORDERS WHERE ORDERS_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            customerId = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customerId;
    }

    public boolean removeOrder(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE ORDERS WHERE ORDERS_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeOrdersByCustomerId(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE ORDERS WHERE CUSTOMERS_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Order parseOrder(ResultSet resultSet) {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt(1));
            order.setTourId(resultSet.getInt(2));
            order.setCustomerId(resultSet.getInt(3));
            order.setDiscountPrise(resultSet.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

}
