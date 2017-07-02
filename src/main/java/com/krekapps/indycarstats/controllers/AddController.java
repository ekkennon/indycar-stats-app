package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
import com.krekapps.indycarstats.models.forms.RaceAddForm;
import com.krekapps.indycarstats.models.forms.SessionAddForm;
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
public class AddController {
    private final String addDriver = "Add IndyCar Driver:";
    private final String addRace = "Add IndyCar Race:";
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
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Drivers without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }
        model.addAttribute("title", addDriver);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new Driver());
        return "drivers/add";

    }

    @RequestMapping(value="drivers/add", method=RequestMethod.POST)
    private String addDriver(Model model, @RequestParam String name, @RequestParam String loggedin, @RequestParam String twitter) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        Driver driver = new Driver(name);
        driver.setTwitterHandle(twitter);
        driverDao.save(driver);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("drivers", driverDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="races/add", method=RequestMethod.GET)
    private String addRace(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Races without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        RaceAddForm raceAddForm = new RaceAddForm(seasonDao.findAll(), trackDao.findAll(), adminSession.isSignedInString());

        model.addAttribute("title", addRace);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", raceAddForm);
        return "races/add";
    }

    @RequestMapping(value="races/add", method=RequestMethod.POST)
    private String addRace(Model model, @ModelAttribute @Valid RaceAddForm raceAddForm, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addRace);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", raceAddForm);
            return "races/add";
        }

        Race race = new Race();
        race.setName(raceAddForm.getName());
        race.setSeason(seasonDao.findOne(raceAddForm.getYear()));
        race.setTrack(trackDao.findOne(raceAddForm.getTrackid()));
        raceDao.save(race);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("races", raceDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="seasons/add", method=RequestMethod.GET)
    private String addSeasonForm(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Seasons without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", "Add IndyCar Season:");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "seasons/add";
    }

    @RequestMapping(value="seasons/add", method=RequestMethod.POST)
    private String addSeason(Model model, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        Iterable<Season> seasons = seasonDao.findAll();
        int newid = 0;
        for (Season s : seasons) {
            if (s.getYear() > newid) {
                newid = s.getYear();
            }
        }
        if (newid > 0) {
            seasonDao.save(new Season(++newid));
        }

        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("seasons", seasonDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="sessions/add", method=RequestMethod.GET)
    private String addSessionFromRace(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Sessions without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        Iterable<Race> races = raceDao.findAll();
        SessionAddForm sessionAddForm = new SessionAddForm(races, adminSession.isSignedInString());

        model.addAttribute("title", addSession);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", sessionAddForm);
        return "sessions/add";
    }

    @RequestMapping(value="sessions/add/{id}", method=RequestMethod.GET)
    private String addSessionFromRace(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Sessions without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        Race race = raceDao.findOne(id);
        SessionAddForm sessionAddForm = new SessionAddForm(race);

        model.addAttribute("title", addSession);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", sessionAddForm);
        return "sessions/add";
    }

    @RequestMapping(value="sessions/add", method=RequestMethod.POST)
    private String addSession(Model model, @ModelAttribute @Valid SessionAddForm sessionAddForm, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addSession);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", sessionAddForm);
            return "sessions/add/" + sessionAddForm.getRaceid();
        }

        Session session = new Session();
        session.setName(sessionAddForm.getName());
        session.setRace(raceDao.findOne(sessionAddForm.getRaceid()));

        sessionDao.save(session);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessionDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/decimal/add", method=RequestMethod.GET)
    private String addDecimalStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new DecimalStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/decimal/add", method=RequestMethod.POST)
    private String addDecimalStat(Model model, @ModelAttribute @Valid DecimalStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        decimalStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", decimalStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/int/add", method=RequestMethod.GET)
    private String addIntStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new IntStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/int/add", method=RequestMethod.POST)
    private String addIntStat(Model model, @ModelAttribute @Valid IntStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        intStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", intStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/string/add", method=RequestMethod.GET)
    private String addStringStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new StringStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/string/add", method=RequestMethod.POST)
    private String addStringStat(Model model, @ModelAttribute @Valid StringStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        stringStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", stringStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="stats/time/add", method=RequestMethod.GET)
    private String addTimeStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new TimeStat());
        return "stats/add";
    }

    @RequestMapping(value="stats/time/add", method=RequestMethod.POST)
    private String addTimeStat(Model model, @ModelAttribute @Valid TimeStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        timeStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", timeStatDao.findAll());
        return "redirect:";
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

    @RequestMapping(value="tracks/add", method=RequestMethod.GET)
    private String addTrack(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Tracks without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addTrack);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new Track());
        return "tracks/add";
    }

    @RequestMapping(value="tracks/add", method=RequestMethod.POST)
    private String addTrack(Model model, @RequestParam String name, @RequestParam String loggedin, @RequestParam String twitter) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        Track track = new Track(name);
        track.setTwitterHandle(twitter);
        trackDao.save(track);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/view";
    }
}
