package com.example.controller;

import com.example.dao.ComplexityDAO;
import com.example.dao.TourDAO;
import com.example.dao.TypeOfTourDAO;
import com.example.dao.VariationDAO;
import com.example.entities.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/tour")
public class TourController {
    @Autowired
    private TourDAO tourDAO;
    @Autowired
    private ComplexityDAO complexityDAO;
    @Autowired
    private VariationDAO variationDAO;
    @Autowired
    private TypeOfTourDAO typeOfTourDAO;

    @GetMapping(value = "/viewAllTours")
    public ModelAndView viewAllTours() {
        ModelAndView mv = new ModelAndView("tour/viewAllTours");
        mv.addObject("ListOfTours", tourDAO.showAllTour());
        mv.addObject("ListOfComplexity", complexityDAO.showAllComplexity());
        mv.addObject("ListOfVariation", variationDAO.showAllVariation());
        mv.addObject("ListOfTypeOfTour", typeOfTourDAO.showAllTypeOfTour());
        return mv;
    }

    @RequestMapping(value = "/addTour", method = RequestMethod.GET)
    public ModelAndView addTour() {
        return new ModelAndView("tour/addTour", "command", new Tour());
    }

    @RequestMapping(value = "/editTour/{id}", method = RequestMethod.GET)
    public ModelAndView editTour(@PathVariable int id) {

        return new ModelAndView("tour/addTour", "command", tourDAO.getTour(id));
    }

    @RequestMapping(value = "/saveTour", method = RequestMethod.POST)
    public ModelAndView saveTour(@ModelAttribute("tour") Tour tour, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("tour/addTour", "command", tour);
        }
        if (tour.getId() == 0) {
            tourDAO.addTour(tour);
        } else {
            tourDAO.editTour(tour);
        }
        return new ModelAndView("redirect:/tour/viewAllTours");
    }

    @GetMapping(value = "/removeTour/{id}")
    public ModelAndView removeTour(@PathVariable int id) {
        tourDAO.removeTour(id);
        return new ModelAndView("redirect:/tour/viewAllTours");
    }


}
