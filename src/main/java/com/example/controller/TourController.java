package com.example.controller;

import com.example.dao.ComplexityDAO;
import com.example.dao.TourDAO;
import com.example.dao.TypeOfTourDAO;
import com.example.dao.VariationDAO;
import com.example.entities.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/tour")
public class TourController {
    private static final TourDAO dao = TourDAO.getInstance();

    @GetMapping(value = "/viewAllTours")
    public ModelAndView viewAllTours() {
        ModelAndView mv = new ModelAndView("tour/viewAllTours");
        mv.addObject("ListOfTours", dao.showAllTour());
        mv.addObject("ListOfComplexity", ComplexityDAO.getInstance().showAllComplexity());
        mv.addObject("ListOfVariation", VariationDAO.getInstance().showAllVariation());
        mv.addObject("ListOfTypeOfTour", TypeOfTourDAO.getInstance().showAllTypeOfTour());
        return mv;
    }

    @RequestMapping(value = "/addTour", method = RequestMethod.GET)
    public ModelAndView addTour() {
        return new ModelAndView("tour/addTour", "command", new Tour());
    }

    @RequestMapping(value = "/editTour/{id}", method = RequestMethod.GET)
    public ModelAndView editTour(@PathVariable int id) {

        return new ModelAndView("tour/addTour", "command", dao.getTour(id));
    }

    @RequestMapping(value = "/saveTour", method = RequestMethod.POST)
    public ModelAndView saveTour(@ModelAttribute("tour") Tour tour, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("tour/addTour", "command", tour);
        }
        if (tour.getId() == 0) {
            dao.addTour(tour);
        } else {
            dao.editTour(tour);
        }
        return new ModelAndView("redirect:/tour/viewAllTours");
    }

    @GetMapping(value = "/removeTour/{id}")
    public ModelAndView removeTour(@PathVariable int id) {
        dao.removeTour(id);
        return new ModelAndView("redirect:/tour/viewAllTours");
    }


}
