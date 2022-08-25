package com.example.controller;


import com.example.dao.TypeOfTourDAO;
import com.example.entities.TypeOfTour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class TypeOfTourController {

    private static final TypeOfTourDAO dao = TypeOfTourDAO.getInstance();

    @GetMapping(value = "/viewAllTypeOfTour")
    public ModelAndView viewAllTypeOfTour() {

        return new ModelAndView("typeOfTour/viewAllTypeOfTour", "ListOfTypeOfTour", dao.showAllTypeOfTour());
    }

    @RequestMapping(value = "/addTypeOfTour", method = RequestMethod.GET)
    public ModelAndView addTypeOfTour() {
        return new ModelAndView("typeOfTour/addTypeOfTour", "command", new TypeOfTour());
    }

    @RequestMapping(value = "/saveTypeOfTour", method = RequestMethod.POST)
    public ModelAndView saveTypeOfTour(@ModelAttribute TypeOfTour typeOfTour) {
        if (typeOfTour.getId() == 0) {
            dao.addTypeOfTour(typeOfTour);
        } else {
            dao.editTypeOfTour(typeOfTour);
        }
        return new ModelAndView("redirect:/viewAllTypeOfTour");
    }

    @RequestMapping(value = "/editTypeOfTour/{id}", method = RequestMethod.GET)
    public ModelAndView editTypeOfTour(@PathVariable int id) {
        return new ModelAndView("typeOfTour/addTypeOfTour", "command", dao.getTypeOfTour(id));
    }

    @GetMapping(value = "/removeTypeOfTour/{id}")
    public ModelAndView removeTypeOfTour(@PathVariable int id) {
        dao.removeTypeOfTour(id);
        return new ModelAndView("redirect:/viewAllTypeOfTour");
    }


}
