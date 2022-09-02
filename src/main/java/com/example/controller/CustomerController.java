package com.example.controller;

import com.example.dao.CustomerDAO;
import com.example.dao.OrderDAO;
import com.example.entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerDAO customerDAO;
    private final OrderDAO orderDAO;

    public CustomerController(CustomerDAO customerDAO, OrderDAO orderDAO) {
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
    }


    @RequestMapping(value = "/viewAllCustomers", method = RequestMethod.GET)
    public ModelAndView viewAllCustomers() {

        return new ModelAndView("customer/viewAllCustomers", "ListOfCustomers", customerDAO.showAllCustomers());
    }


    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public ModelAndView addCustomer() {

        return new ModelAndView("customer/addCustomer", "command", new Customer());
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public ModelAndView saveCustomer(@ModelAttribute("customer") @Valid Customer customer,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return new ModelAndView("customer/addCustomer", "command", customer);
        } else {
            if (customer.getId() == 0) {
                customerDAO.addCustomer(customer);
            } else {
                customerDAO.editCustomer(customer);
            }
            return new ModelAndView("redirect:/customer/viewAllCustomers");

        }

    }

    @RequestMapping(value = "/editCustomer/{id}", method = RequestMethod.GET)
    public ModelAndView editCustomer(@PathVariable int id) {
        return new ModelAndView("customer/addCustomer", "command", customerDAO.getCustomerById(id));
    }

    @GetMapping(value = "/removeCustomer/{id}")
    public ModelAndView removeCustomer(@PathVariable int id) {
        orderDAO.removeOrdersByCustomerId(id);
        customerDAO.removeCustomer(id);
        return new ModelAndView("redirect:/customer/viewAllCustomers");
    }

}
