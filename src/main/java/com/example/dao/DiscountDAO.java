package com.example.dao;

import com.example.entities.Discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAO {
    private final static Connection connection = OracleDAOFactoryImpl.getConnection();

    public List<Discount> showAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DISCOUNT");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                discounts.add(parseDiscount(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    public Discount getDiscount(int id) {
        ResultSet resultSet = null;
        Discount discount = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM DISCOUNT WHERE DISCOUNT_ID = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                discount = parseDiscount(resultSet);
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
        return discount;
    }

    public boolean addDiscount(Discount discount) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO DISCOUNT VALUES (?, ?, ?, ?) ")) {
            preparedStatement.setInt(1, discount.getId());
            preparedStatement.setInt(2, discount.getParticipationNumber());
            preparedStatement.setInt(3, discount.getPercent());
            //todo id  auto
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeDiscount(int id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE DISCOUNT WHERE DISCOUNT_ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Discount parseDiscount(ResultSet resultSet) {
        Discount discount = new Discount();
        try {
            discount.setId(resultSet.getInt(1));
            discount.setParticipationNumber(resultSet.getInt(2));
            discount.setPercent(resultSet.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }

}
