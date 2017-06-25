package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.data.RaceDao;
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
@RequestMapping(value="races")
public class RaceController {
    private String addTitle = "Add IndyCar Race:";
    private String viewListTitle = "IndyCar Races List";

    @Autowired
    private RaceDao raceDao;

    @RequestMapping(value="")
    private String index(Model model) {
        model.addAttribute("title", "IndyCar Races");
        return "races/index";
    }

    @RequestMapping(value="view")
    private String viewAll(Model model) {
        model.addAttribute("title", viewListTitle);
        model.addAttribute("races", raceDao.findAll());
        return "races/view";
    }

    @RequestMapping(value="view/{id}")
    private String viewOne(Model model, @PathVariable int id) {
        Race race = raceDao.findOne(id);
        ArrayList<Race> racelist = new ArrayList<>();
        racelist.add(race);
        Iterable<Race> d = racelist;
        model.addAttribute("title", "IndyCar Race: " + race.getName());
        model.addAttribute("races", d);
        return "races/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("title", addTitle);
        model.addAttribute(new Race());
        return "races/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid Race race, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            return "races/add";
        }

        raceDao.save(race);
        model.addAttribute("title", viewListTitle);
        model.addAttribute("races", raceDao.findAll());
        return "races/view";
    }
}
