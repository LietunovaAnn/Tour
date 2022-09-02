package com.example.controller;

import com.example.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class OrderController {

    private final OrderDAO orderDAO;
    private final CustomerDAO customerDAO;
    private final TourDAO tourDAO;
    private final ComplexityDAO complexityDAO;
    private final VariationDAO variationDAO;
    private final TypeOfTourDAO typeOfTourDAO;

    public OrderController(OrderDAO orderDAO, CustomerDAO customerDAO, TourDAO tourDAO, ComplexityDAO complexityDAO, VariationDAO variationDAO, TypeOfTourDAO typeOfTourDAO) {
        this.orderDAO = orderDAO;
        this.customerDAO = customerDAO;
        this.tourDAO = tourDAO;
        this.complexityDAO = complexityDAO;
        this.variationDAO = variationDAO;
        this.typeOfTourDAO = typeOfTourDAO;
    }

    @RequestMapping(value = "/viewAllCustomersAndOrders", method = RequestMethod.GET)
    public ModelAndView viewAllCustomersAndOrders() {
        ModelAndView mv = new ModelAndView("order/viewAllCustomersAndOrders");

        mv.addObject("ListOfOrders", orderDAO.showAllOrder());
        mv.addObject("ListOfCustomers", customerDAO.showAllCustomers());
        mv.addObject("ListOfTour", tourDAO.showAllTour());
        mv.addObject("ListOfComplexity", complexityDAO.showAllComplexity());
        mv.addObject("ListOfVariation", variationDAO.showAllVariation());
        mv.addObject("ListOfTypeOfTour", typeOfTourDAO.showAllTypeOfTour());
        return mv;
    }

//    @RequestMapping(value = "/findCustomer/{id}", method = RequestMethod.GET)
//    public ModelAndView findCustomersAndOrdersByCustomerId(@PathVariable int id) {
//        ModelAndView mv = new ModelAndView("order/findCustomer");
//
//        mv.addObject("ListOfOrders", ORDER_DAO.showAllOrder());
//        mv.addObject("ListOfCustomers", CustomerDAO.getInstance().showAllCustomers());
//        mv.addObject("ListOfTour", TourDAO.getInstance().showAllTour());
//        mv.addObject("ListOfComplexity", ComplexityDAO.getInstance().showAllComplexity());
//        mv.addObject("ListOfVariation", VariationDAO.getInstance().showAllVariation());
//        mv.addObject("ListOfTypeOfTour", TypeOfTourDAO.getInstance().showAllTypeOfTour());
//        return mv;
//    }

//    @RequestMapping(value = "/editOrder/{id}", method = RequestMethod.GET)
//    public ModelAndView editOrder(@PathVariable int id) {
//
//        return new ModelAndView("order/addOrder", "command", dao.getOrder(id));
//    }

//    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
//    public ModelAndView saveOrder(@ModelAttribute Order order) {
//        dao.addOrder(order);
//        return new ModelAndView("redirect:/order/viewAllCustomersAndOrders");
//    }

    @GetMapping(value = "/removeOrder/{id}")
    public ModelAndView removeOrder(@PathVariable int id) {

        orderDAO.removeOrder(id);
        return new ModelAndView("redirect:/viewAllCustomersAndOrders");
    }
}
