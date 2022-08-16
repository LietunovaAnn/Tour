package com.example.controller;

import com.example.dao.*;
import com.example.entities.Complexity;
import com.example.entities.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/tour")
public class TourController {
    private static final DAOFactory FACTORY = OracleDAOFactoryImpl.getInstance();
    private static final TourDAO TOUR_DAO = FACTORY.getTourDAO();
    private static final ComplexityDAO COMPLEXITY_DAO = FACTORY.getComplexityDAO();
    private static final VariationDAO VARIATION_DAO = FACTORY.getVariationDAO();
    private static final TypeOfTourDAO TYPE_OF_TOUR_DAO = FACTORY.getTypeOfTourDAO();
    private List<Tour> tourList;


    @GetMapping(value = "/viewAllTours")
    public ModelAndView viewAllTours() {
        ModelAndView mv = new ModelAndView("tour/viewAllTours");

        tourList = TOUR_DAO.showAllTour();
        mv.addObject("ListOfTours", tourList);
        for (Tour tour : tourList) {
            Complexity complexity = COMPLEXITY_DAO.getComplexity(tour.getComplexityId());

            complexity.getName();
            mv.addObject("getComplexity", complexity);
//            List<String> totName = new ArrayList<>();
//            for (Variation variation :VARIATION_DAO.getVariation(tour.getId())) {
//                totName.add(TYPE_OF_TOUR_DAO.getTypeOfTour(variation.getTypeOfTourId()).getName());
//            }
//            mv.addObject("ListOfTypeOfTour", totName);

        }
        return mv;
        //  return new ModelAndView("tour/viewAllTours", "ListOfTours", tourList);
    }

//    @GetMapping(value = "/viewAllTours/{id}")
//    public ModelAndView infoTours(@PathVariable int id) {
//        ComplexityController complexityController = new ComplexityController();
//
//        return new ModelAndView("tour/viewAllTours", "getComplexity",
//                complexityController.getComplexity(tourList.get(id).getId()));
//    }

    @GetMapping(value = "/chooseTour/{id}")
    public ModelAndView chooseTour(@PathVariable int id) {
        tourList = TOUR_DAO.showAllTour();
        return new ModelAndView("tour/viewAllTours", "ListOfTours", tourList);
    }

    @RequestMapping(value = "/addTour", method = RequestMethod.GET)
    public ModelAndView addTour() {
        return new ModelAndView("tour/addTour", "command", new Tour());
    }

    @RequestMapping(value = "/editTour/{id}", method = RequestMethod.GET)
    public ModelAndView editTour(@PathVariable int id) {

        return new ModelAndView("tour/addTour", "command", TOUR_DAO.getTour(id));
    }

    @RequestMapping(value = "/saveTour", method = RequestMethod.POST)
    public ModelAndView saveTour(@ModelAttribute Tour tour) {
        TOUR_DAO.addTour(tour);
        return new ModelAndView("redirect:/tour/viewAllTours");
    }

    @GetMapping(value = "/removeTour/{id}")
    public ModelAndView removeTour(@PathVariable int id) {
        TOUR_DAO.removeTour(id);
//        for (int i = 0; i < tourList.size(); i++) {
//            if (id == tourList.get(i).getId()) {
//                tourList.remove(tourList.get(i));
//            }
//        }
        return new ModelAndView("redirect:/tour/viewAllTours");
    }


}
