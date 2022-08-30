package com.example.dao;

import com.example.entities.TypeOfTour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeOfTourDAO {
    private final static Connection connection = OracleDAOFactoryImpl.getInstance().getConnection();
    private static TypeOfTourDAO instance;
    private TypeOfTourDAO() {
    }
    public static TypeOfTourDAO getInstance() {
        if (instance == null) {
            instance = new TypeOfTourDAO();
        }
        return instance;
    }

    public List<TypeOfTour> showAllTypeOfTour() {
        List<TypeOfTour> types = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TYPE_OF_TOUR");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                types.add(parseTypeOfTour(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public TypeOfTour getTypeOfTour(int id) {
        ResultSet resultSet = null;
        TypeOfTour typeOfTour = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM TYPE_OF_TOUR WHERE TYPE_OF_TOUR_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                typeOfTour = parseTypeOfTour(resultSet);
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
        return typeOfTour;
    }

    public boolean addTypeOfTour(TypeOfTour typeOfTour) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO TYPE_OF_TOUR VALUES (TYPE_OF_TOUR_SEQ.nextval, ?) ")) {
            preparedStatement.setString(1, typeOfTour.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean editTypeOfTour(TypeOfTour typeOfTour) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE DISCOUNT set TYPE_OF_TOUR_NAME = ? WHERE TYPE_OF_TOUR_ID = ?")) {
            preparedStatement.setString(1, typeOfTour.getName());
            preparedStatement.setInt(2, typeOfTour.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeTypeOfTour(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE TYPE_OF_TOUR WHERE TYPE_OF_TOUR_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private TypeOfTour parseTypeOfTour(ResultSet resultSet) {
        TypeOfTour typeOfTour = new TypeOfTour();
        try {
            typeOfTour.setId(resultSet.getInt(1));
            typeOfTour.setName(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeOfTour;
    }
}
