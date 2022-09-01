package com.example.controller;


import com.example.dao.DiscountDAO;
import com.example.entities.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class DiscountController {
    @Autowired
    private DiscountDAO discountDAO;

    @GetMapping(value = "/viewAllDiscounts")
    public ModelAndView viewAllTours() {

        return new ModelAndView("discount/viewAllDiscounts", "ListOfDiscounts", discountDAO.showAllDiscounts());
    }

    @RequestMapping(value = "/addDiscount", method = RequestMethod.GET)
    public ModelAndView addDiscount() {
        return new ModelAndView("discount/addDiscount", "command", new Discount());
    }

    @RequestMapping(value = "/saveDiscount", method = RequestMethod.POST)
    public ModelAndView saveDiscount(@ModelAttribute Discount discount, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("discount/addDiscount", "command", discount);
        }
        if (discount.getId() == 0) {
            discountDAO.addDiscount(discount);
        } else {
            discountDAO.editDiscount(discount);
        }
        return new ModelAndView("redirect:/viewAllDiscounts");
    }

    @RequestMapping(value = "/editDiscount/{id}", method = RequestMethod.GET)
    public ModelAndView editDiscount(@PathVariable int id) {
        return new ModelAndView("discount/addDiscount", "command", discountDAO.getDiscountById(id));
    }

    @GetMapping(value = "/removeDiscount/{id}")
    public ModelAndView removeDiscount(@PathVariable int id) {
        discountDAO.removeDiscount(id);
        return new ModelAndView("redirect:/viewAllDiscounts");
    }

}
