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
    private static final CustomerDAO CUSTOMER_DAO = CustomerDAO.getInstance();
    private static final OrderDAO ORDER_DAO = OrderDAO.getInstance();


    @RequestMapping(value = "/viewAllCustomers", method = RequestMethod.GET)
    public ModelAndView viewAllCustomers() {

        return new ModelAndView("customer/viewAllCustomers", "ListOfCustomers", CUSTOMER_DAO.showAllCustomers());
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
                CUSTOMER_DAO.addCustomer(customer);
            } else {
                CUSTOMER_DAO.editCustomer(customer);
            }
            return new ModelAndView("redirect:/customer/viewAllCustomers");

        }

    }

    @RequestMapping(value = "/editCustomer/{id}", method = RequestMethod.GET)
    public ModelAndView editCustomer(@PathVariable int id) {
        return new ModelAndView("customer/addCustomer", "command", CUSTOMER_DAO.getCustomerById(id));
    }

    @GetMapping(value = "/removeCustomer/{id}")
    public ModelAndView removeCustomer(@PathVariable int id) {
        ORDER_DAO.removeOrdersByCustomerId(id);
        CUSTOMER_DAO.removeCustomer(id);
        return new ModelAndView("redirect:/customer/viewAllCustomers");
    }

}
