package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
import com.krekapps.indycarstats.models.forms.TeamAddForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class TeamController {
    private final String addTeam = "Add IndyCar Team:";

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private TeamDao teamDao;

    @RequestMapping(value="teams")
    public String teamIndex(Model model) {
        model.addAttribute("title", "IndyCar Teams");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "teams/index";
    }

    @RequestMapping(value="teams/list")
    private String teamsViewAll(Model model) {
        model.addAttribute("title", "IndyCar Teams List");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("teams", teamDao.findAll());
        return "teams/list";
    }

    @RequestMapping(value="teams/view/{id}")
    private String teamsViewOne(Model model, @PathVariable int id) {
        Team team = teamDao.findOne(id);
        List<Team> teamlist = new ArrayList<>();
        teamlist.add(team);
        Iterable<Team> t = teamlist;
        model.addAttribute("title", "IndyCar Team: " + team.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("teams", t);
        return "teams/view";
    }

    @RequestMapping(value="teams/add", method=RequestMethod.GET)
    private String addTeam(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Teams without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        TeamAddForm teamAddForm = new TeamAddForm(seasonDao.findAll(), driverDao.findAll(), adminSession.isSignedInString());

        model.addAttribute("title", addTeam);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", teamAddForm);
        return "teams/add";
    }

    @RequestMapping(value="teams/add", method=RequestMethod.POST)
    private String addTeam(Model model, @ModelAttribute @Valid TeamAddForm teamAddForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(teamAddForm.getLoggedin())) {
            adminSession.setSignedIn(teamAddForm.getLoggedin());
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addTeam);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", teamAddForm);
            return "teams/add";
        }

        List<Driver> drivers = new ArrayList<>();
        int[] driverids = teamAddForm.getDriverids();

        for (int id : driverids) {
            drivers.add(driverDao.findOne(id));
        }

        Team team = new Team();
        team.setTwitterHandle(teamAddForm.getTwitterHandle());
        team.setName(teamAddForm.getName());
        team.setSeason(seasonDao.findOne(teamAddForm.getYear()));
        team.setDrivers(drivers);

        teamDao.save(team);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("teams", teamDao.findAll());
        return "redirect:";
    }
}
