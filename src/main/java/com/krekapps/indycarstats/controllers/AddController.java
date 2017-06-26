package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.Track;
import com.krekapps.indycarstats.models.data.DriverDao;
import com.krekapps.indycarstats.models.data.TrackDao;
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

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class AddController {
    private final String addDriver = "Add IndyCar Driver:";
    private final String addTrack = "Add IndyCar Track:";

    @Autowired
    private TrackDao trackDao;

    @Autowired
    private DriverDao driverDao;

    @RequestMapping(value="drivers/add", method=RequestMethod.GET)
    private String addDriver(Model model) {
        model.addAttribute("title", addDriver);
        model.addAttribute(new Driver());
        return "drivers/add";
    }

    @RequestMapping(value="drivers/add", method=RequestMethod.POST)
    private String addDriver(Model model, @ModelAttribute @Valid Driver driver, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addDriver);
            return "drivers/add";
        }
        driverDao.save(driver);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("drivers", driverDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="tracks/add", method=RequestMethod.GET)
    private String addTrack(Model model) {
        model.addAttribute("title", addTrack);
        model.addAttribute(new Track());
        return "tracks/add";
    }

    @RequestMapping(value="tracks/add", method=RequestMethod.POST)
    private String addTrack(Model model, @ModelAttribute @Valid Track track, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addTrack);
            return "tracks/add";
        }

        trackDao.save(track);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/view";
    }
}
