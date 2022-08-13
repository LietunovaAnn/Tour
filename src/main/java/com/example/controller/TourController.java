package com.example.controller;

import com.example.dao.DAOFactory;
import com.example.dao.OracleDAOFactoryImpl;
import com.example.entities.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/tour")
public class TourController {
    private static final DAOFactory dao = OracleDAOFactoryImpl.getInstance();
    private List<Tour> tourtList;


//    @GetMapping(value = "/viewAllTours")
//    public ModelAndView viewAllTours () {
//        tourtList = (tourtList != null) ? tourtList : getAllTours();
//        return new ModelAndView("viewAllTours", "ListOfTours", tourtList);
//    }

    @GetMapping(value = "/viewAllTours")
    public ModelAndView infoTours() {
        tourtList = (tourtList != null) ? tourtList : getAllTours();
        return new ModelAndView("tour/viewAllTours", "ListOfTours", tourtList);
    }

    @GetMapping(value = "/chooseTour/{id}")
    public ModelAndView chooseTour(@PathVariable int id) {
        tourtList = (tourtList != null) ? tourtList : getAllTours();
        return new ModelAndView("tour/viewAllTours", "ListOfTours", tourtList);
    }

    @RequestMapping(value = "/addTour", method = RequestMethod.GET)
    public ModelAndView addTour() {
        return new ModelAndView("tour/addTour", "command", new Tour());
    }

    @RequestMapping(value = "/editTour/{id}", method = RequestMethod.GET)
    public ModelAndView editTour(@PathVariable int id) {

        return new ModelAndView("tour/editTour", "command", new Tour());
    }

    @RequestMapping(value = "/saveTour", method = RequestMethod.POST)
    public ModelAndView saveTour(@ModelAttribute Tour tour) {
        tourtList.add(tour);
        return new ModelAndView("redirect:/tour/viewAllTours");
    }

    @GetMapping(value = "/removeTour/{id}")
    public ModelAndView removeTour(@PathVariable int id) {
        for (int i = 0; i < tourtList.size(); i++) {
            if (id == tourtList.get(i).getId()) {
                tourtList.remove(tourtList.get(i));
            }
        }
        return new ModelAndView("redirect:/tour/viewAllTours");
    }

    public List<Tour> getAllTours() {
        List<Tour> tourtList = dao.getTourDAO().showAllTour();
        return tourtList;
    }
}
