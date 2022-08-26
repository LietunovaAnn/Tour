package com.example.controller;

import com.example.dao.*;
import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class TourRegistrationController {
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final CustomerDAO customerDAO = CustomerDAO.getInstance();
    private final DiscountDAO discountDAO = DiscountDAO.getInstance();

    private Tour tour;


    @RequestMapping(value = "/tourRegistration/{tourId}", method = RequestMethod.GET)
    public ModelAndView tourRegistration(@PathVariable int tourId) {
        this.tour = TourDAO.getInstance().getTour(tourId);
        ModelAndView mv = new ModelAndView("tourRegistration", "tour", tour);
        mv.addObject("complexity", ComplexityDAO.getInstance().getComplexity(tour.getComplexityId()));
        mv.addObject("ListVariation", VariationDAO.getInstance().getVariation(tour.getId()));
        mv.addObject("ListOfTypeOfTour", TypeOfTourDAO.getInstance().showAllTypeOfTour());
        mv.addObject("customer", new Customer());
        return mv;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ModelAndView addOrder(@ModelAttribute("customer") Customer customer) {
        Customer customerFromDb = customerDAO.getCustomer(customer);
        if (customerFromDb == null) {
            customerDAO.addCustomer(customer);
            customerFromDb = customerDAO.getCustomer(customer);
        }


        Order order = new Order();
        order.setTourId(tour.getId());
        order.setCustomerId(customerFromDb.getId());
        order.setDiscountPrise(customerDAO.editParticipationNumberCustomer(customerFromDb));

        int priceWithDiscount = tour.getPrice();
        if (discountDAO.getDiscountByParticipationNumber(customerFromDb.getParticipationNumber()) != null) {
            int percent = discountDAO.getDiscountByParticipationNumber(customerFromDb.getParticipationNumber()).getPercent();
            if (percent != 0) {
                priceWithDiscount *= (double) (100 - percent) / 100;
            }
        }
        order.setDiscountPrise(priceWithDiscount);
        orderDAO.addOrder(order);
        return new ModelAndView("redirect:/");
    }

}
