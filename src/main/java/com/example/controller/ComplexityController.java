package com.example.controller;

import com.example.dao.ComplexityDAO;
import com.example.entities.Complexity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class ComplexityController {

    private static final ComplexityDAO COMPLEXITY_DAO = ComplexityDAO.getInstance();

    @GetMapping(value = "/viewAllComplexity")
    public ModelAndView viewAllComplexity() {

        return new ModelAndView("complexity/viewAllComplexity", "ListOfComplexity", COMPLEXITY_DAO.showAllComplexity());
    }

    @RequestMapping(value = "/addComplexity", method = RequestMethod.GET)
    public ModelAndView addComplexity() {
        return new ModelAndView("complexity/addComplexity", "command", new Complexity());
    }

    @RequestMapping(value = "/editComplexity/{id}", method = RequestMethod.GET)
    public ModelAndView editComplexity(@PathVariable int id) {
        return new ModelAndView("complexity/addComplexity", "command", COMPLEXITY_DAO.getComplexity(id));
    }

    @RequestMapping(value = "/saveComplexity", method = RequestMethod.POST)
    public ModelAndView saveComplexity(@ModelAttribute Complexity complexity) {
        if (complexity.getId() == 0) {
            COMPLEXITY_DAO.addComplexity(complexity);
        } else {
            COMPLEXITY_DAO.editComplexity(complexity);
        }
        return new ModelAndView("redirect:/viewAllComplexity");
    }

    @GetMapping(value = "/removeComplexity/{id}")
    public ModelAndView removeComplexity(@PathVariable int id) {
        COMPLEXITY_DAO.removeComplexity(id);
        return new ModelAndView("redirect:/viewAllComplexity");
    }

}
