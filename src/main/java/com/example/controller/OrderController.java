package com.example.controller;

import com.example.dao.*;
import com.example.entities.Order;
import com.example.entities.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class OrderController {

    private static final OrderDAO dao = OrderDAO.getInstance();

    @RequestMapping(value = "/viewAllCustomersAndOrders", method = RequestMethod.GET)
    public ModelAndView viewAllCustomersAndOrders() {
        ModelAndView mv = new ModelAndView("order/viewAllCustomersAndOrders");

        mv.addObject("ListOfOrders", dao.showAllOrder());
        mv.addObject("ListOfCustomers", CustomerDAO.getInstance().showAllCustomers());
        mv.addObject("ListOfTour", TourDAO.getInstance().showAllTour());
        mv.addObject("ListOfComplexity", ComplexityDAO.getInstance().showAllComplexity());
        mv.addObject("ListOfVariation", VariationDAO.getInstance().showAllVariation());
        mv.addObject("ListOfTypeOfTour", TypeOfTourDAO.getInstance().showAllTypeOfTour());
        return mv;
    }
    @RequestMapping(value = "/addOrder/{tour}", method = RequestMethod.POST)
    public ModelAndView addOrder(@PathVariable Tour tour) {

        ModelAndView mv = new ModelAndView("order/addOrder", "command", new Order());
        mv.addObject("Tour", TourDAO.getInstance().getTour(tour.getId()));


//        mv.addObject("Complexity", ComplexityDAO.getInstance().getComplexity(tour.getComplexityId()));
//        mv.addObject("Variation", VariationDAO.getInstance().getVariation(tour.getId()));
//        mv.addObject("ListOfTypeOfTour", TypeOfTourDAO.getInstance().showAllTypeOfTour());
        //   mv.addObject("Customer", CustomerDAO.getInstance().getCustomer())

        return mv;
    }

    @RequestMapping(value = "/editOrder/{id}", method = RequestMethod.GET)
    public ModelAndView editOrder(@PathVariable int id) {

        return new ModelAndView("order/addOrder", "command", dao.getOrder(id));
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public ModelAndView saveOrder(@ModelAttribute Order order) {
        dao.addOrder(order);
        return new ModelAndView("redirect:/order/viewAllCustomersAndOrders");
    }

    @GetMapping(value = "/removeOrder/{id}")
    public ModelAndView removeOrder(@PathVariable int id) {
        dao.removeOrder(id);
        return new ModelAndView("redirect:/order/viewAllCustomersAndOrders");
    }
}
