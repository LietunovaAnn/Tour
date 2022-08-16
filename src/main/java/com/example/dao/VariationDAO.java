package com.example.dao;

import com.example.entities.Variation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VariationDAO {
    private final static Connection connection = OracleDAOFactoryImpl.getConnection();

    public List<Variation> showAllVariation() {
        List<Variation> variations = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM VARIATION");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                variations.add(parseVariation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return variations;
    }

    public List<Variation> getVariation(int id) {
        ResultSet resultSet = null;
        List<Variation> variation = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM VARIATION WHERE TOUR_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                variation.add(parseVariation(resultSet));
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
        return variation;
    }

    public boolean addVariation(Variation variation) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO VARIATION VALUES (?, ?) ")) {
            preparedStatement.setInt(1, variation.getTourId());
            preparedStatement.setInt(2, variation.getTypeOfTourId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeVariation(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE VARIATION WHERE TOUR_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Variation parseVariation(ResultSet resultSet) {
        Variation variation = new Variation();
        try {
            variation.setTourId(resultSet.getInt(1));
            variation.setTypeOfTourId(resultSet.getInt(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return variation;
    }
}
