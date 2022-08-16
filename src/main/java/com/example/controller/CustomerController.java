package com.example.controller;

import com.example.dao.CustomerDAO;
import com.example.dao.DAOFactory;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    private static final DAOFactory FACTORY = OracleDAOFactoryImpl.getInstance();
    private static final CustomerDAO CUSTOMER_DAO = FACTORY.getCustomerDAO();
    private List<Customer> customerList;


    @RequestMapping(value = "/viewAllCustomers", method = RequestMethod.GET)
    public ModelAndView viewAllCustomers() {
        customerList = CUSTOMER_DAO.showAllCustomers();
        return new ModelAndView("viewAllCustomers", "ListOfCustomers", customerList);
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public ModelAndView addCustomer() {

        return new ModelAndView("customer/addCustomer", "command", new Customer());
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public ModelAndView saveCustomer(@ModelAttribute Customer customer) {
        if (customer.getId() == 0) {
            CUSTOMER_DAO.addCustomer(customer);
        } else {
            CUSTOMER_DAO.editCustomer(customer);
        }
        return new ModelAndView("redirect:/viewAllCustomers");
    }

    @RequestMapping(value = "/editCustomer/{id}", method = RequestMethod.GET)
    public ModelAndView editCustomer(@PathVariable int id) {
        return new ModelAndView("customer/addCustomer", "command", CUSTOMER_DAO.getCustomer(id));
    }

    @GetMapping(value = "/removeCustomer/{id}")
    public ModelAndView removeCustomer(@PathVariable int id) {
        CUSTOMER_DAO.removeCustomer(id);
        return new ModelAndView("redirect:/viewAllCustomers");
    }

}
