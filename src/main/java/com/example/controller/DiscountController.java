package com.example.controller;

import com.example.dao.DAOFactory;
import com.example.dao.DiscountDAO;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.entities.Discount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class DiscountController {
    private static final DAOFactory dao = OracleDAOFactoryImpl.getInstance();
    private List<Discount> discounts;

    @GetMapping(value = "/discounts")
    public ModelAndView viewAllTours() {
        discounts = (discounts != null) ? discounts : getDiscounts();
        return new ModelAndView("discount/discounts", "ListOfDiscounts", discounts);
    }

    @GetMapping(value = "/removeDiscount/{id}")
    public ModelAndView removeDiscount(@PathVariable int id) {
        for (int i = 0; i < discounts.size(); i++) {
            if (id == discounts.get(i).getId()) {
                discounts.remove(discounts.get(i));
            }
        }
        return new ModelAndView("redirect:/discounts");
    }

    public List<Discount> getDiscounts() {
        DiscountDAO discountDAO = dao.getDiscountDAO();
        return discountDAO.showAllDiscounts();
    }
}
