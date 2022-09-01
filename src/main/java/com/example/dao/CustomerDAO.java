package com.example.dao;

import com.example.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAO {
    @Autowired
    private OracleDAOFactoryImpl oracleDAOFactory;

    @Autowired
    public CustomerDAO() {
    }


    public List<Customer> showAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection().prepareStatement("SELECT * FROM CUSTOMERS ORDER BY CUSTOMERS_ID");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                customerList.add(parseCustomer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    public Customer getCustomerById(int id) {
        ResultSet resultSet = null;
        Customer customer = null;
        try (PreparedStatement preparedStatement =
                     oracleDAOFactory.getConnection().prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMERS_ID = ?")) {
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

    public Customer getCustomer(Customer customer) {
        ResultSet resultSet = null;
        Customer customerFromDb = null;
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection()
                .prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMERS_NAME = ? and CUSTOMERS_EMAIL = ?")) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerFromDb = parseCustomer(resultSet);
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
        return customerFromDb;
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
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection().prepareStatement(
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
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection().prepareStatement
                ("UPDATE CUSTOMERS set CUSTOMERS_NAME = ?, CUSTOMERS_EMAIL = ?, PARTICIPATION_NUMBER = ?" +
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

    public boolean editCustomerParticipationNumber(Customer customer) {
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection().prepareStatement
                ("UPDATE CUSTOMERS set PARTICIPATION_NUMBER = ?" +
                        " WHERE CUSTOMERS_ID = ?")) {
            customer.setParticipationNumber(customer.getParticipationNumber() - 1);
            preparedStatement.setInt(1, customer.getParticipationNumber());
            preparedStatement.setInt(2, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int editParticipationNumberCustomer(Customer customer) {
        int participationNumber = customer.getParticipationNumber() + 1;
        customer.setParticipationNumber(participationNumber);
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection().prepareStatement
                ("UPDATE CUSTOMERS set PARTICIPATION_NUMBER = ? WHERE CUSTOMERS_ID = ?")) {
            preparedStatement.setInt(1, customer.getParticipationNumber());
            preparedStatement.setInt(2, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participationNumber;
    }

    public boolean removeCustomer(int id) {
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection()
                .prepareStatement("DELETE CUSTOMERS WHERE CUSTOMERS_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
