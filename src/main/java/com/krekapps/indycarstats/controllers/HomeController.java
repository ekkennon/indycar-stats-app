package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Team;
import com.krekapps.indycarstats.models.data.RaceDao;
import com.krekapps.indycarstats.models.data.SeasonDao;
import com.krekapps.indycarstats.models.data.TeamDao;
import com.krekapps.indycarstats.models.forms.AddDataForm;
import com.krekapps.indycarstats.models.forms.GeneralForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class HomeController {

    @Autowired
    RaceDao raceDao;

    @Autowired
    SeasonDao seasonDao;

    @Autowired
    TeamDao teamDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "index";
    }

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Admin Page for IndyCar Stats");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "login";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public String login(Model model, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        AddDataForm addDataForm = new AddDataForm();
        addDataForm.setLoggedin(adminSession.isSignedInString());
        addDataForm.setSeasons(seasonDao.findAll());

        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("form", addDataForm);
        return "add";
    }

    @RequestMapping(value="view")
    public String view(Model model) {
        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "view";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid AddDataForm form, Errors errors) {
        if (!adminSession.isSignedInString().equals(form.getLoggedin())) {
            adminSession.setSignedIn(form.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "IndyCar Stats App - Add Stats");
            model.addAttribute("form", form);
            return "add";
        }

        AddDataForm addDataForm = new AddDataForm();
        addDataForm.setLoggedin(form.getLoggedin());
        addDataForm.setRaces(raceDao.findBySeason(seasonDao.findOne(form.getYear())));

        for (Team t : teamDao.findBySeason(seasonDao.findOne(form.getYear()))) {
            for (Driver d : t.getDrivers()) {
                addDataForm.addDriver(d);
            }
        }

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("form", addDataForm);
        return "/stats/addByDriver";
    }
}
