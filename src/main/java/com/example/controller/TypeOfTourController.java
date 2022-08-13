package com.example.controller;

import com.example.dao.DAOFactory;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.dao.TypeOfTourDAO;
import com.example.entities.TypeOfTour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class TypeOfTourController {
    private static final DAOFactory dao = OracleDAOFactoryImpl.getInstance();
    private List<TypeOfTour> typeOfTours;

    @GetMapping(value = "/typeOfTour")
    public ModelAndView viewAllTypeOfTour() {
        typeOfTours = (typeOfTours != null) ? typeOfTours : getTypeOfTour();
        return new ModelAndView("typeOfTour", "ListOfTypeOfTour", typeOfTours);
    }

    @GetMapping(value = "/removeTypeOfTour/{id}")
    public ModelAndView removeTypeOfTour(@PathVariable int id) {
        for (int i = 0; i < typeOfTours.size(); i++) {
            if (id == typeOfTours.get(i).getId()) {
                typeOfTours.remove(typeOfTours.get(i));
            }
        }
        return new ModelAndView("redirect:/typeOfTour");
    }

    public List<TypeOfTour> getTypeOfTour() {
        TypeOfTourDAO typeOfTourDAO = dao.getTypeOfTourDAO();
        return typeOfTourDAO.showAllTypeOfTour();
    }
}
