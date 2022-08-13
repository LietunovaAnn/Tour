package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/info")
public class InfoController {

    @GetMapping
    public ModelAndView viewAllStudents() {
        return new ModelAndView("info");
    }

}
