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

    public List<Customer> showAllCustomers() {
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

    public Customer getCustomer(int id) {
        ResultSet resultSet = null;
        Customer customer = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMERS_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = parseCustomer(resultSet);
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
        return customer;
    }

    private Customer parseCustomer(ResultSet resultSet) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO CUSTOMERS VALUES (CUSTOMERS_SEQ.nextval, ?, ?, ?) ")) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setInt(3, customer.getParticipationNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean editCustomer(Customer customer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE DISCOUNT set CUSTOMERS_NAME = ?, CUSTOMERS_EMAIL = ?, PARTICIPATION_NUMBER = ?" +
                        " WHERE CUSTOMERS_ID = ?")) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setInt(3, customer.getParticipationNumber());
            preparedStatement.setInt(4, customer.getId());
            preparedStatement.executeUpdate();
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
