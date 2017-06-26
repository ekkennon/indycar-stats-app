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
import java.util.List;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class ListController {
    private String viewListTitle = "IndyCar Drivers List";

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="drivers/view")
    private String driversViewAll(Model model) {
        model.addAttribute("title", "IndyCar Drivers List");
        model.addAttribute("drivers", driverDao.findAll());
        return "drivers/view";
    }

    @RequestMapping(value="drivers/view/{id}")
    private String driversViewOne(Model model, @PathVariable int id) {
        Driver driver = driverDao.findOne(id);
        List<Driver> driverlist = new ArrayList<>();
        driverlist.add(driver);
        Iterable<Driver> d = driverlist;
        model.addAttribute("title", "IndyCar Driver: " + driver.getName());
        model.addAttribute("drivers", d);
        return "drivers/view";
    }

    @RequestMapping(value="tracks/view")
    private String tracksViewAll(Model model) {
        model.addAttribute("title", "IndyCar Tracks List");
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/view";
    }

    @RequestMapping(value="tracks/view/{id}")
    private String tracksViewOne(Model model, @PathVariable int id) {
        Track track = trackDao.findOne(id);
        List<Track> tracklist = new ArrayList<>();
        tracklist.add(track);
        Iterable<Track> t = tracklist;
        model.addAttribute("title", "IndyCar Track: " + track.getName());
        model.addAttribute("tracks", t);
        return "tracks/view";
    }
}
