package com.example.controller;

import com.example.dao.ComplexityDAO;
import com.example.entities.Complexity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class ComplexityController {
    private final ComplexityDAO complexityDAO;

    public ComplexityController(ComplexityDAO complexityDAO) {
        this.complexityDAO = complexityDAO;
    }

    @GetMapping(value = "/viewAllComplexity")
    public ModelAndView viewAllComplexity() {

        return new ModelAndView("complexity/viewAllComplexity", "ListOfComplexity", complexityDAO.showAllComplexity());
    }

    @GetMapping(value = "/addComplexity")
    public ModelAndView addComplexity() {
        return new ModelAndView("complexity/addComplexity", "command", new Complexity());
    }

    @GetMapping(value = "/editComplexity/{id}")
    public ModelAndView editComplexity(@PathVariable int id) {
        return new ModelAndView("complexity/addComplexity", "command", complexityDAO.getComplexity(id));
    }

    @PostMapping(value = "/saveComplexity")
    public ModelAndView saveComplexity(@ModelAttribute Complexity complexity) {
        if (complexity.getId() == 0) {
            complexityDAO.addComplexity(complexity);
        } else {
            complexityDAO.editComplexity(complexity);
        }
        return new ModelAndView("redirect:/viewAllComplexity");
    }

    @GetMapping(value = "/removeComplexity/{id}")
    public ModelAndView removeComplexity(@PathVariable int id) {
        complexityDAO.removeComplexity(id);
        return new ModelAndView("redirect:/viewAllComplexity");
    }

}
