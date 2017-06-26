package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
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
    private final String addRace = "Add IndyCar Race:";
    private final String addSeason = "Add IndyCar Season:";
    private final String addSession = "Add IndyCar Session:";
    private final String addStat = "Add IndyCar Stat:";
    private final String addTeam = "Add IndyCar Team:";
    private final String addTrack = "Add IndyCar Track:";

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private RaceDao raceDao;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private DecimalStatDao decimalStatDao;

    @Autowired
    private IntStatDao intStatDao;

    @Autowired
    private StringStatDao stringStatDao;

    @Autowired
    private TimeStatDao timeStatDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private TrackDao trackDao;

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

    @RequestMapping(value="races/add", method=RequestMethod.GET)
    private String addRace(Model model) {
        model.addAttribute("title", addRace);
        model.addAttribute(new Race());
        return "races/add";
    }

    @RequestMapping(value="races/add", method=RequestMethod.POST)
    private String addRace(Model model, @ModelAttribute @Valid Race race, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addRace);
            return "races/add";
        }

        raceDao.save(race);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("races", raceDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="seasons/add", method=RequestMethod.GET)
    private String addSeason(Model model) {
        model.addAttribute("title", addSeason);
        model.addAttribute(new Season());
        return "seasons/add";
    }

    @RequestMapping(value="seasons/add", method=RequestMethod.POST)
    private String addSeason(Model model, @ModelAttribute @Valid Season season, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addSeason);
            return "seasons/add";
        }
        seasonDao.save(season);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("seasons", seasonDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="sessions/add", method=RequestMethod.GET)
    private String addSession(Model model) {
        model.addAttribute("title", addSession);
        model.addAttribute(new Session());
        return "sessions/add";
    }

    @RequestMapping(value="sessions/add", method=RequestMethod.POST)
    private String addSession(Model model, @ModelAttribute @Valid Session session, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addSession);
            return "sessions/add";
        }

        sessionDao.save(session);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("sessions", sessionDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/decimal/add", method=RequestMethod.GET)
    private String addDecimalStat(Model model) {
        model.addAttribute("title", addStat);
        model.addAttribute(new DecimalStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/decimal/add", method=RequestMethod.POST)
    private String addDecimalStat(Model model, @ModelAttribute @Valid DecimalStat stat, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            return "stats/add";
        }
        decimalStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("stats", decimalStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/int/add", method=RequestMethod.GET)
    private String addIntStat(Model model) {
        model.addAttribute("title", addStat);
        model.addAttribute(new IntStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/int/add", method=RequestMethod.POST)
    private String addIntStat(Model model, @ModelAttribute @Valid IntStat stat, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            return "stats/add";
        }
        intStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("stats", intStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/string/add", method=RequestMethod.GET)
    private String addStringStat(Model model) {
        model.addAttribute("title", addStat);
        model.addAttribute(new StringStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/string/add", method=RequestMethod.POST)
    private String addStringStat(Model model, @ModelAttribute @Valid StringStat stat, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            return "stats/add";
        }
        stringStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("stats", stringStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/time/add", method=RequestMethod.GET)
    private String addTimeStat(Model model) {
        model.addAttribute("title", addStat);
        model.addAttribute(new TimeStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/time/add", method=RequestMethod.POST)
    private String addTimeStat(Model model, @ModelAttribute @Valid TimeStat stat, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            return "stats/add";
        }
        timeStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("stats", timeStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="teams/add", method=RequestMethod.GET)
    private String addTeam(Model model) {
        model.addAttribute("title", addTeam);
        model.addAttribute(new Team());
        return "teams/add";
    }

    @RequestMapping(value="teams/add", method=RequestMethod.POST)
    private String addTeam(Model model, @ModelAttribute @Valid Team team, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addTeam);
            return "teams/add";
        }

        teamDao.save(team);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("teams", teamDao.findAll());
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
