package com.example.controller;

import com.example.dao.CustomerDAO;
import com.example.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    private static final CustomerDAO dao = CustomerDAO.getInstance();


    @RequestMapping(value = "/viewAllCustomers", method = RequestMethod.GET)
    public ModelAndView viewAllCustomers() {

        return new ModelAndView("customer/viewAllCustomers", "ListOfCustomers", dao.showAllCustomers());
    }


    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public ModelAndView addCustomer() {

        return new ModelAndView("customer/addCustomer", "command", new Customer());
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public ModelAndView saveCustomer(@ModelAttribute Customer customer) {
        if (customer.getId() == 0) {
            dao.addCustomer(customer);
        } else {
            dao.editCustomer(customer);
        }
        return new ModelAndView("redirect:/customer/viewAllCustomers");
    }

    @RequestMapping(value = "/editCustomer/{id}", method = RequestMethod.GET)
    public ModelAndView editCustomer(@PathVariable int id) {
        return new ModelAndView("customer/addCustomer", "command", dao.getCustomerById(id));
    }

    @GetMapping(value = "/removeCustomer/{id}")
    public ModelAndView removeCustomer(@PathVariable int id) {
        dao.removeCustomer(id);
        return new ModelAndView("redirect:/customer/viewAllCustomers");
    }

}
