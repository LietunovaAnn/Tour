package com.example.dao;

import com.example.entities.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TourDAO {
    @Autowired
    private OracleDAOFactoryImpl oracleDAOFactory;

    @Autowired
    public TourDAO() {
    }

    public List<Tour> showAllTour() {
        List<Tour> tourList = new ArrayList<>();
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection()
                .prepareStatement("SELECT * FROM TOUR");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                tourList.add(parseTour(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tourList;
    }

    private Tour parseTour(ResultSet resultSet) {
        Tour tour = new Tour();
        try {
            tour.setId(resultSet.getInt(1));
            tour.setName(resultSet.getString(2));
            tour.setPrice(resultSet.getInt(3));
            tour.setComplexityId(resultSet.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour;
    }

    public Tour getTour(int id) {
        ResultSet resultSet = null;
        Tour tour = null;
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection()
                .prepareStatement("SELECT * FROM TOUR WHERE TOUR_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tour = parseTour(resultSet);
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
        return tour;
    }

    public boolean addTour(Tour tour) {
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection()
                .prepareStatement("INSERT INTO TOUR VALUES (TOUR_SEQ.nextval, ?, ?, ?) ")) {
            preparedStatement.setString(1, tour.getName());
            preparedStatement.setInt(2, tour.getPrice());
            preparedStatement.setInt(3, tour.getComplexityId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean editTour(Tour tour) {
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection()
                .prepareStatement("UPDATE TOUR set TOUR_NAME = ?, TOUR_PRICE = ?, COMPLEXITY_ID = ? WHERE TOUR_ID = ?")) {
            preparedStatement.setString(1, tour.getName());
            preparedStatement.setInt(2, tour.getPrice());
            preparedStatement.setInt(3, tour.getComplexityId());
            preparedStatement.setInt(4, tour.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeTour(int id) {
        try (PreparedStatement preparedStatement = oracleDAOFactory.getConnection()
                .prepareStatement("DELETE TOUR WHERE TOUR_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
