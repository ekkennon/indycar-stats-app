package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.data.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
@RequestMapping(value="drivers")
public class DriverController {
    private String addTitle = "Add IndyCar Driver:";
    private String viewListTitle = "IndyCar Drivers List";

    @Autowired
    private DriverDao driverDao;

    @RequestMapping(value="")
    private String index(Model model) {
        model.addAttribute("title", "IndyCar Drivers");
        return "drivers/index";
    }

    @RequestMapping(value="view")
    private String viewAll(Model model) {
        Iterable<Driver> d = driverDao.findAll();
        model.addAttribute("title", viewListTitle);
        model.addAttribute("drivers", driverDao.findAll());
        return "drivers/view";
    }

    @RequestMapping(value="view/{id}")
    private String viewOne(Model model, @PathVariable int id) {
        Driver driver = driverDao.findOne(id);
        ArrayList<Driver> driverlist = new ArrayList<>();
        driverlist.add(driver);
        Iterable<Driver> d = driverlist;
        model.addAttribute("title", "IndyCar Driver: " + driver.getName());
        model.addAttribute("drivers", d);
        return "drivers/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("title", addTitle);
        model.addAttribute(new Driver());
        return "drivers/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid Driver driver, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            return "drivers/add";
        }

        driverDao.save(driver);
        model.addAttribute("title", viewListTitle);
        model.addAttribute("drivers", driverDao.findAll());
        return "drivers/view";
    }
}
