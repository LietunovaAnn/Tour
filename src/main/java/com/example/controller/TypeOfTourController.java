package com.example.controller;

import com.example.dao.DAOFactory;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.dao.TypeOfTourDAO;
import com.example.entities.TypeOfTour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class TypeOfTourController {
    private static final DAOFactory FACTORY = OracleDAOFactoryImpl.getInstance();
    private static final TypeOfTourDAO TYPE_OF_TOUR_DAO = FACTORY.getTypeOfTourDAO();
    private List<TypeOfTour> typeOfTours;

    @GetMapping(value = "/typeOfTour")
    public ModelAndView viewAllTypeOfTour() {
        typeOfTours = TYPE_OF_TOUR_DAO.showAllTypeOfTour();
        return new ModelAndView("typeOfTour/typeOfTour", "ListOfTypeOfTour", typeOfTours);
    }

    @RequestMapping(value = "/addTypeOfTour", method = RequestMethod.GET)
    public ModelAndView addTypeOfTour() {
        return new ModelAndView("typeOfTour/addTypeOfTour", "command", new TypeOfTour());
    }

    @RequestMapping(value = "/saveTypeOfTour", method = RequestMethod.POST)
    public ModelAndView saveTypeOfTour(@ModelAttribute TypeOfTour typeOfTour) {
        if (typeOfTour.getId() == 0) {
            TYPE_OF_TOUR_DAO.addTypeOfTour(typeOfTour);
        } else {
            TYPE_OF_TOUR_DAO.editTypeOfTour(typeOfTour);
        }
        return new ModelAndView("redirect:/typeOfTour");
    }

    @RequestMapping(value = "/editTypeOfTour/{id}", method = RequestMethod.GET)
    public ModelAndView editTypeOfTour(@PathVariable int id) {
        return new ModelAndView("typeOfTour/addTypeOfTour", "command", TYPE_OF_TOUR_DAO.getTypeOfTour(id));
    }

    @GetMapping(value = "/removeTypeOfTour/{id}")
    public ModelAndView removeTypeOfTour(@PathVariable int id) {
        TYPE_OF_TOUR_DAO.removeTypeOfTour(id);
        return new ModelAndView("redirect:/typeOfTour");
    }


}
