package com.example.dao;

public interface DAOFactory extends AutoCloseable {
    // void connect();
    void disconnect();

    TourDAO getTourDAO();

    OrderDAO getOrderDAO();

    CustomerDAO getCustomerDAO();

    ComplexityDAO getComplexityDAO();

    DiscountDAO getDiscountDAO();

    TypeOfTourDAO getTypeOfTourDAO();

    VariationDAO getVariationDAO();
}
