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

    public List<Order> showAllTour() {
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

    public Order getOrder(int id) {
        ResultSet resultSet = null;
        Order order = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM ORDERS WHERE ORDER_ID = ?")) {
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
