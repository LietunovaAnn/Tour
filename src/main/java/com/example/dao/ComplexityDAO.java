package com.example.dao;

import com.example.entities.Complexity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplexityDAO {
    private static ComplexityDAO instance;
    private final static Connection connection = OracleDAOFactoryImpl.getConnection();

    private ComplexityDAO() {
    }

    public static ComplexityDAO getInstance() {
        if (instance == null) {
            instance = new ComplexityDAO();
        }
        return instance;
    }

    public List<Complexity> showAllComplexity() {
        List<Complexity> complexityList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM COMPLEXITY");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                complexityList.add(parseComplexity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complexityList;
    }

    public Complexity getComplexity(int id) {
        ResultSet resultSet = null;
        Complexity complexity = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM COMPLEXITY WHERE COMPLEXITY_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                complexity = parseComplexity(resultSet);
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
        return complexity;
    }

    public boolean addComplexity(Complexity complexity) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO COMPLEXITY  VALUES (COMPLEXITY_SEQ.nextval, ?) ")) {
            preparedStatement.setString(1, complexity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean editComplexity(Complexity complexity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE DISCOUNT set COMPLEXITY_NAME = ? WHERE COMPLEXITY_ID = ?")) {
            preparedStatement.setString(1, complexity.getName());
            preparedStatement.setInt(2, complexity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeComplexity(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE COMPLEXITY WHERE COMPLEXITY_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Complexity parseComplexity(ResultSet resultSet) {
        Complexity complexity = new Complexity();
        try {
            complexity.setId(resultSet.getInt(1));
            complexity.setName(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complexity;
    }

}
