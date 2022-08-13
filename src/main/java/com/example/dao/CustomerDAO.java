package com.example.dao;

import com.example.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private final static Connection connection = OracleDAOFactoryImpl.getConnection();

    public static List<Customer> showAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMERS");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                customerList.add(parseCustomer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    private static Customer parseCustomer(ResultSet resultSet) {
        Customer customer = new Customer();
        try {
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setEmail(resultSet.getString(3));
            customer.setParticipationNumber(resultSet.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean addCustomer(Customer customer) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO CUSTOMERS VALUES (?, ?, ?, ?) ")) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setInt(4, customer.getParticipationNumber());
            //todo id i part auto
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeCustomer(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE CUSTOMERS WHERE CUSTOMERS_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
