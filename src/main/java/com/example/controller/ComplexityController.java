package com.example.controller;

import com.example.dao.ComplexityDAO;
import com.example.dao.DAOFactory;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.entities.Complexity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class ComplexityController {
    private static final DAOFactory dao = OracleDAOFactoryImpl.getInstance();
    private List<Complexity> complexityList;

    @GetMapping(value = "/complexity")
    public ModelAndView viewAllComplexity() {
        complexityList = (complexityList != null) ? complexityList : getAllComplexity();
        return new ModelAndView("complexity/complexity", "ListOfComplexity", complexityList);
    }

    @GetMapping(value = "/complexity/{id}")
    public ModelAndView chooseComplexity(@PathVariable int id) {
        Complexity complexity = getComplexity(id);
        return new ModelAndView("complexity/complexity", "getComplexity", complexity);
    }


    @GetMapping(value = "/removeComplexity/{id}")
    public ModelAndView removeComplexity(@PathVariable int id) {
        for (int i = 0; i < complexityList.size(); i++) {
            if (id == complexityList.get(i).getId()) {
                complexityList.remove(complexityList.get(i));
            }
        }
        return new ModelAndView("redirect:/complexity");
    }

    public List<Complexity> getAllComplexity() {
        ComplexityDAO complexityDAO = dao.getComplexityDAO();
        return complexityDAO.showAllComplexity();
    }

    public Complexity getComplexity(int id) {
        ComplexityDAO complexityDAO = dao.getComplexityDAO();
        return complexityDAO.getComplexity(id);
    }
}
