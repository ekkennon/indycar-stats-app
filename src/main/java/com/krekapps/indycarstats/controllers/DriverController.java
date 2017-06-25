package com.krekapps.indycarstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
@RequestMapping(value="drivers")
public class DriverController {

    @RequestMapping(value="")
    private String index(Model model) {
        model.addAttribute("title", "IndyCar Drivers");
        return "drivers/index";
    }

    @RequestMapping(value="view")
    private String viewAll(Model model) {
        model.addAttribute("title", "IndyCar Drivers List");
        return "drivers/view";
    }

    @RequestMapping(value="view/{id}")
    private String viewOne(Model model, @PathVariable int id) {
        model.addAttribute("title", "IndyCar Driver: ");
        return "drivers/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("title", "Add IndyCar Driver");
        return "drivers/add";
    }
}
