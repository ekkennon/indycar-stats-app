package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
import com.krekapps.indycarstats.models.forms.AddDataForm;
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
 * Created by ekk on 07-Jul-17.
 */

@Controller
@RequestMapping(value="stats")
public class StatController {
    private static AddDataForm addDataForm = new AddDataForm();

    private final String addStat = "Add IndyCar Stat:";
    private String statsTitle = "IndyCar Stats List";

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private RaceDao raceDao;

    @Autowired
    SeasonDao seasonDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private StatDao statDao;

    @Autowired
    TeamDao teamDao;

    @RequestMapping(value="")
    public String statIndex(Model model) {
        model.addAttribute("title", "IndyCar Stats");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "stats/index";
    }

    @RequestMapping(value="addSeason", method=RequestMethod.GET)
    public String chooseDriver(Model model, @RequestParam int year) {
        if (!adminSession.isSignedInString().equals(adminSession.isSignedInString())) {
            adminSession.setSignedIn(adminSession.isSignedInString());
        }

        addDataForm.setLoggedin(adminSession.isSignedInString());
        addDataForm.setRaces(raceDao.findBySeason(seasonDao.findOne(year)));

        for (Team t : teamDao.findBySeason(seasonDao.findOne(year))) {
            for (Driver d : t.getDrivers()) {
                addDataForm.addDriver(d);
            }
        }

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("form", addDataForm);
        return "/stats/addByDriver";
    }

    @RequestMapping(value="addDriver", method=RequestMethod.GET)
    public String chooseRace(Model model, @RequestParam int driver) {
        if (!adminSession.isSignedInString().equals(adminSession.isSignedInString())) {
            adminSession.setSignedIn(adminSession.isSignedInString());
        }

        addDataForm.setDriverid(driver);

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("form", addDataForm);
        return "/stats/addByRace";
    }

    @RequestMapping(value="addRace", method=RequestMethod.GET)
    private String chooseSession(Model model, @RequestParam int race) {
        if (!adminSession.isSignedInString().equals(adminSession.isSignedInString())) {
            adminSession.setSignedIn(adminSession.isSignedInString());
        }

        addDataForm.setRaceid(race);
        addDataForm.setSessions(sessionDao.findByRace(raceDao.findOne(race)));

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("form", addDataForm);

        return "stats/addBySession";
    }

    @RequestMapping(value="addSession", method=RequestMethod.GET)
    private String addData(Model model, @RequestParam int id) {
        if (!adminSession.isSignedInString().equals(adminSession.isSignedInString())) {
            adminSession.setSignedIn(adminSession.isSignedInString());
        }

        addDataForm.setSessionid(id);

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("form", addDataForm);

        return "stats/addData";
    }

    @RequestMapping(value="submitdata", method=RequestMethod.POST)
    private String submitData(Model model, @ModelAttribute @Valid AddDataForm form, Errors errors) {
        if (errors.hasErrors()) {
            //model.addAttribute("title", addTitle);
            //model.addAttribute("form", raceForm);
            return "redirect:";
        }

        DriverResult stat = new DriverResult();
        stat.setCarNum(form.getStat().getCarNum());
        stat.setStartPos(form.getStat().getStartPos());
        stat.setEndPos(form.getStat().getEndPos());
        stat.setStatus(form.getStat().getStatus());
        stat.setDriverid(addDataForm.getDriverid());
        stat.setSessionid(addDataForm.getSessionid());
        statDao.save(stat);

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("stats", statDao.findAll());

        return "/stats/list";
    }

    @RequestMapping(value="viewchart")
    private String viewChart(Model model) {

        model.addAttribute("title", "IndyCar Stats App - View Stats");
        model.addAttribute("chartName", "View Stats Chart");

        return "/stats/viewChart";
    }

    @RequestMapping(value="viewSeason", method=RequestMethod.GET)
    public String chooseSeason(Model model) {
        if (!adminSession.isSignedInString().equals(adminSession.isSignedInString())) {
            adminSession.setSignedIn(adminSession.isSignedInString());
        }

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("seasons", seasonDao.findAll());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "/stats/viewBySeason";
        //TODO ajax is not working for this
    }

    @RequestMapping(value="viewBySeason", method=RequestMethod.POST)
    public String viewBySeason(Model model, @RequestParam int year) {
        if (!adminSession.isSignedInString().equals(adminSession.isSignedInString())) {
            adminSession.setSignedIn(adminSession.isSignedInString());
        }

        List<Driver> drivers = new ArrayList<>();
        for (Team t : teamDao.findBySeason(seasonDao.findOne(year))) {
            drivers.addAll(t.getDrivers());
        }

        StringBuilder json = new StringBuilder();
        json.append("{\"columns\":[{\"name\":\"drivername\",\"type\":\"string\"},{\"name\":\"points\",\"type\":\"number\"}],\"rows\":[");
        for (Driver d : drivers) {
            json.append("{\"drivername\":\"");
            json.append(d.getName());
            json.append("\",\"points\":");

            int points = 0;
            Iterable<DriverResult> stats = statDao.findByDriverid(d.getId());
            for (DriverResult stat : stats) {

                if (raceDao.findOne(sessionDao.findOne(stat.getSessionid()).getRace().getId()).getSeason().getYear() == year) {
                    points += stat.getPoints();
                }
            }

            json.append(points);
            json.append("},");
        }

        json.deleteCharAt(json.length()-1);//delete final ,
        json.append("]}");

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("chartName", "View Stats Chart");
        model.addAttribute("json", json);
        return "/stats/viewChart";
    }
}
