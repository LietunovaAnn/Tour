package com.example.controller;

import com.example.dao.ComplexityDAO;
import com.example.dao.DAOFactory;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.entities.Complexity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class ComplexityController {
    private static final DAOFactory FACTORY = OracleDAOFactoryImpl.getInstance();
    private static final ComplexityDAO COMPLEXITY_DAO = FACTORY.getComplexityDAO();
    private List<Complexity> complexityList;

    @GetMapping(value = "/complexity")
    public ModelAndView viewAllComplexity() {
        complexityList = COMPLEXITY_DAO.showAllComplexity();
        return new ModelAndView("complexity/complexity", "ListOfComplexity", complexityList);
    }

    //    @GetMapping(value = "/complexity/{id}")
//    public ModelAndView chooseComplexity(@PathVariable int id) {
//       // Complexity complexity = getComplexity(id);
//        return new ModelAndView("complexity/complexity", "getComplexity", complexity);
//    }
    @RequestMapping(value = "/addComplexity", method = RequestMethod.GET)
    public ModelAndView addComplexity() {
        return new ModelAndView("complexity/addComplexity", "command", new Complexity());
    }

    @RequestMapping(value = "/editComplexity/{id}", method = RequestMethod.GET)
    public ModelAndView editComplexity(@PathVariable int id) {
        return new ModelAndView("complexity/editComplexity", "command", COMPLEXITY_DAO.getComplexity(id));
    }

    @RequestMapping(value = "/saveComplexity", method = RequestMethod.POST)
    public ModelAndView saveTour(@ModelAttribute Complexity complexity) {
        if (complexity.getId() == 0) {
            COMPLEXITY_DAO.addComplexity(complexity);
        } else {
            COMPLEXITY_DAO.editComplexity(complexity);
        }

        return new ModelAndView("redirect:/complexity");
    }

    @GetMapping(value = "/removeComplexity/{id}")
    public ModelAndView removeComplexity(@PathVariable int id) {
        COMPLEXITY_DAO.removeComplexity(id);
        return new ModelAndView("redirect:/complexity");
    }

}
