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
import java.util.List;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class ListController {
    private String statsTitle = "IndyCar Stats List";

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

    @RequestMapping(value="races/list")
    private String racesList(Model model) {
        model.addAttribute("title", "IndyCar Races List");
        model.addAttribute("races", raceDao.findAll());
        return "races/list";
    }

    @RequestMapping(value="seasons/list")
    private String seasonsList(Model model) {
        model.addAttribute("title", "IndyCar Seasons List");
        model.addAttribute("seasons", seasonDao.findAll());
        return "seasons/list";
    }

    @RequestMapping(value="sessions/list")
    private String sessionsViewAll(Model model) {
        model.addAttribute("title", "IndyCar Sessions List");
        model.addAttribute("sessions", sessionDao.findAll());//TODO here and /races/view/{id} display race info also
        return "sessions/list";
    }

    @RequestMapping(value="sessions/view/{id}")
    private String sessionsViewOne(Model model, @PathVariable int id) {
        Session session = sessionDao.findOne(id);
        List<Session> sessionlist = new ArrayList<>();
        sessionlist.add(session);
        Iterable<Session> s = sessionlist;
        model.addAttribute("title", "IndyCar Session: " + session.getName());//TODO display year + trackname + sessionname
        model.addAttribute("sessions", s);
        return "sessions/view";
    }

    @RequestMapping(value="stats/decimal/view")
    private String statsDecimalViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("stats", decimalStatDao.findAll());
        return "stats/view";
    }

    @RequestMapping(value="stats/int/view")
    private String statsIntViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("stats", intStatDao.findAll());
        return "stats/view";
    }

    @RequestMapping(value="stats/string/view")
    private String statsDtringViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("stats", stringStatDao.findAll());
        return "stats/view";
    }

    @RequestMapping(value="stats/time/view")
    private String statsTimeViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("stats", timeStatDao.findAll());
        return "stats/view";
    }

    @RequestMapping(value="stats/decimal/view/{id}")
    private String statsDecimalViewOne(Model model, @PathVariable int id) {
        DecimalStat stat = decimalStatDao.findOne(id);
        List<DecimalStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<DecimalStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="stats/int/view/{id}")
    private String statsIntViewOne(Model model, @PathVariable int id) {
        IntStat stat = intStatDao.findOne(id);
        List<IntStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<IntStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="stats/string/view/{id}")
    private String statsStringViewOne(Model model, @PathVariable int id) {
        StringStat stat = stringStatDao.findOne(id);
        List<StringStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<StringStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="stats/time/view/{id}")
    private String statsTimeViewOne(Model model, @PathVariable int id) {
        TimeStat stat = timeStatDao.findOne(id);
        List<TimeStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<TimeStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="teams/view")
    private String teamsViewAll(Model model) {
        model.addAttribute("title", "IndyCar Teams List");
        model.addAttribute("teams", teamDao.findAll());
        return "teams/view";
    }

    @RequestMapping(value="teams/view/{id}")
    private String teamsViewOne(Model model, @PathVariable int id) {
        Team team = teamDao.findOne(id);
        List<Team> teamlist = new ArrayList<>();
        teamlist.add(team);
        Iterable<Team> t = teamlist;
        model.addAttribute("title", "IndyCar Team: " + team.getName());
        model.addAttribute("teams", t);
        return "teams/view";
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
