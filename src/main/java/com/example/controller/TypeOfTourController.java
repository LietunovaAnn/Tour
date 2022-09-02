package com.example.controller;


import com.example.dao.TypeOfTourDAO;
import com.example.entities.TypeOfTour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class TypeOfTourController {

    private final TypeOfTourDAO typeOfTourDAO;

    public TypeOfTourController(TypeOfTourDAO typeOfTourDAO) {
        this.typeOfTourDAO = typeOfTourDAO;
    }

    @GetMapping(value = "/viewAllTypeOfTour")
    public ModelAndView viewAllTypeOfTour() {

        return new ModelAndView("typeOfTour/viewAllTypeOfTour", "ListOfTypeOfTour", typeOfTourDAO.showAllTypeOfTour());
    }

    @RequestMapping(value = "/addTypeOfTour", method = RequestMethod.GET)
    public ModelAndView addTypeOfTour() {
        return new ModelAndView("typeOfTour/addTypeOfTour", "command", new TypeOfTour());
    }

    @RequestMapping(value = "/saveTypeOfTour", method = RequestMethod.POST)
    public ModelAndView saveTypeOfTour(@ModelAttribute TypeOfTour typeOfTour) {
        if (typeOfTour.getId() == 0) {
            typeOfTourDAO.addTypeOfTour(typeOfTour);
        } else {
            typeOfTourDAO.editTypeOfTour(typeOfTour);
        }
        return new ModelAndView("redirect:/viewAllTypeOfTour");
    }

    @RequestMapping(value = "/editTypeOfTour/{id}", method = RequestMethod.GET)
    public ModelAndView editTypeOfTour(@PathVariable int id) {
        return new ModelAndView("typeOfTour/addTypeOfTour", "command", typeOfTourDAO.getTypeOfTour(id));
    }

    @GetMapping(value = "/removeTypeOfTour/{id}")
    public ModelAndView removeTypeOfTour(@PathVariable int id) {
        typeOfTourDAO.removeTypeOfTour(id);
        return new ModelAndView("redirect:/viewAllTypeOfTour");
    }


}
