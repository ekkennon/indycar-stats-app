package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
import com.krekapps.indycarstats.models.forms.DriverInTeam;
import com.krekapps.indycarstats.models.forms.TeamForm;
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
@RequestMapping(value="teams")
public class TeamController {
    private final String addTitle = "Add IndyCar Team:";
    private final String indexTitle = "IndyCar Teams";
    private final String listTitle = indexTitle + " List";
    private final String singleTitle = "IndyCar Team: ";
    private final String noPrivilageTitle = "Unable to Add/Edit Teams without Admin Privilage.";
    private final String editTitle = "Edit " + singleTitle;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private TeamDao teamDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", indexTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "teams/index";
    }

    @RequestMapping(value="list")
    private String viewAll(Model model) {
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("teams", teamDao.findAll());
        return "teams/list";
    }

    @RequestMapping(value="view/{id}")
    private String viewById(Model model, @PathVariable int id) {
        Team team = teamDao.findOne(id);
        model.addAttribute("title", singleTitle + team.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("team", team);
        return "teams/view";
    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    private String add(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "teams/index";
        }

        TeamForm teamForm = new TeamForm(seasonDao.findAll(), driverDao.findAll(), adminSession.isSignedInString());

        model.addAttribute("title", addTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", teamForm);
        return "teams/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid TeamForm teamForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(teamForm.getLoggedin())) {
            adminSession.setSignedIn(teamForm.getLoggedin());
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", teamForm);
            return "teams/add";
        }

        List<Driver> drivers = new ArrayList<>();
        int[] driverids = teamForm.getDriverids();

        for (int id : driverids) {
            drivers.add(driverDao.findOne(id));
        }

        Team team = new Team();
        team.setTwitterHandle(teamForm.getTwitterHandle());
        team.setName(teamForm.getName());
        team.setSeason(seasonDao.findOne(teamForm.getYear()));
        team.setDrivers(drivers);

        teamDao.save(team);
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("teams", teamDao.findAll());
        return "teams/list";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    private String edit(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "teams/index";
        }

        Team team = teamDao.findOne(id);

        TeamForm teamForm = new TeamForm(seasonDao.findAll(), driverDao.findAll(), adminSession.isSignedInString());
        teamForm.setName(team.getName());
        teamForm.setYear(team.getSeason().getYear());
        teamForm.setTwitterHandle(team.getTwitterHandle());
        teamForm.setId(id);
        teamForm.setLoggedin(adminSession.isSignedInString());

        List<DriverInTeam> drivers = new ArrayList<>();
        for (Driver d : driverDao.findAll()) {
            DriverInTeam driver = new DriverInTeam(d.getId(),d.getName(), team.getDrivers().contains(d));
            drivers.add(driver);
        }

        model.addAttribute("title", editTitle + team.getName());
        model.addAttribute("form", teamForm);
        model.addAttribute("driversInTeam", drivers);

        return "teams/edit";
    }

    @RequestMapping(value="edit/**", method=RequestMethod.POST)
    private String edit(Model model, @ModelAttribute @Valid TeamForm teamForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(teamForm.getLoggedin())) {
            adminSession.setSignedIn(teamForm.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", editTitle + teamForm.getName());
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", teamForm);
            return "teams/edit/" + teamForm.getId();
        }

        List<Driver> drivers = new ArrayList<>();
        int[] driverids = teamForm.getDriverids();

        for (int id : driverids) {
            drivers.add(driverDao.findOne(id));
        }

        Team team = teamDao.findOne(teamForm.getId());
        team.setTwitterHandle(teamForm.getTwitterHandle());
        team.setName(teamForm.getName());
        team.setSeason(seasonDao.findOne(teamForm.getYear()));
        team.setDrivers(drivers);

        teamDao.save(team);
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("teams", teamDao.findAll());
        return "teams/list";
    }

    @RequestMapping(value="remove/{id}")
    private String remove(Model model, @PathVariable int id, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        teamDao.delete(id);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("teams", teamDao.findAll());
        return "teams/list";
    }
}
