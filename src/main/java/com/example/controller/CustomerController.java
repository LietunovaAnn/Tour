package com.example.controller;

import com.example.dao.DAOFactory;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    private final DAOFactory dao = OracleDAOFactoryImpl.getInstance();
    private List<Customer> customerList;


    @RequestMapping(value = "/viewAllCustomers", method = RequestMethod.GET)
    public ModelAndView viewAllCustomers() {
        customerList = (customerList != null) ? customerList : getAllCustomers();
        return new ModelAndView("viewAllCustomers", "ListOfCustomers", customerList);
    }


    public List<Customer> getAllCustomers() {
        customerList = dao.getCustomerDAO().showAllCustomers();
        return customerList;
    }
}
