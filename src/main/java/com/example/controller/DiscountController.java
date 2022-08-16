package com.example.controller;

import com.example.dao.DAOFactory;
import com.example.dao.DiscountDAO;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.entities.Discount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class DiscountController {
    private static final DAOFactory FACTORY = OracleDAOFactoryImpl.getInstance();
    private static final DiscountDAO DISCOUNT_DAO = FACTORY.getDiscountDAO();
    private List<Discount> discounts;

    @GetMapping(value = "/discounts")
    public ModelAndView viewAllTours() {
        discounts = DISCOUNT_DAO.showAllDiscounts();
        return new ModelAndView("discount/discounts", "ListOfDiscounts", discounts);
    }

    @RequestMapping(value = "/addDiscount", method = RequestMethod.GET)
    public ModelAndView addDiscount() {
        return new ModelAndView("discount/addDiscount", "command", new Discount());
    }

    @RequestMapping(value = "/saveDiscount", method = RequestMethod.POST)
    public ModelAndView saveTour(@ModelAttribute Discount discount) {
        if (discount.getId() == 0) {
            DISCOUNT_DAO.addDiscount(discount);
        } else {
            DISCOUNT_DAO.editDiscount(discount);
        }
        return new ModelAndView("redirect:/discounts");
    }

    @RequestMapping(value = "/editDiscount/{id}", method = RequestMethod.GET)
    public ModelAndView editDiscount(@PathVariable int id) {
        return new ModelAndView("discount/addDiscount", "command", DISCOUNT_DAO.getDiscount(id));
    }

    @GetMapping(value = "/removeDiscount/{id}")
    public ModelAndView removeDiscount(@PathVariable int id) {
        DISCOUNT_DAO.removeDiscount(id);
        return new ModelAndView("redirect:/discounts");
    }

}
