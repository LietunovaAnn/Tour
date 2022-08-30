package com.example.controller;


import com.example.dao.DiscountDAO;
import com.example.entities.Discount;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class DiscountController {

    private static final DiscountDAO dao = DiscountDAO.getInstance();

    @GetMapping(value = "/viewAllDiscounts")
    public ModelAndView viewAllTours() {

        return new ModelAndView("discount/viewAllDiscounts", "ListOfDiscounts", dao.showAllDiscounts());
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
            dao.addDiscount(discount);
        } else {
            dao.editDiscount(discount);
        }
        return new ModelAndView("redirect:/viewAllDiscounts");
    }

    @RequestMapping(value = "/editDiscount/{id}", method = RequestMethod.GET)
    public ModelAndView editDiscount(@PathVariable int id) {
        return new ModelAndView("discount/addDiscount", "command", dao.getDiscountById(id));
    }

    @GetMapping(value = "/removeDiscount/{id}")
    public ModelAndView removeDiscount(@PathVariable int id) {
        dao.removeDiscount(id);
        return new ModelAndView("redirect:/viewAllDiscounts");
    }

}
