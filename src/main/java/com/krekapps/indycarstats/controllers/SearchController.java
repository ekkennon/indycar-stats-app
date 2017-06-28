package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Season;
import com.krekapps.indycarstats.models.Session;
import com.krekapps.indycarstats.models.data.RaceDao;
import com.krekapps.indycarstats.models.data.SeasonDao;
import com.krekapps.indycarstats.models.data.TrackDao;
import com.krekapps.indycarstats.models.data.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class SearchController {
    private String addTitle = "Add IndyCar Track:";
    private String viewListTitle = "IndyCar Track Sessions";

    @Autowired
    private RaceDao raceDao;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="races/view/{id}")
    private String racesViewOne(Model model, @PathVariable int id) {
        Race race = raceDao.findOne(id);
        Iterable<Session> sessions = sessionDao.findByRace(race);
        model.addAttribute("title", "IndyCar Race: " + race.getSeason().getYear() + " " + race.getTrack().getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessions);
        return "sessions/list";
    }

    @RequestMapping(value="seasons/view/{id}")
    private String seasonsViewOne(Model model, @PathVariable int id) {
        Season season = seasonDao.findOne(id);
        model.addAttribute("title", "IndyCar Season: " + season.getYear());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("season", season);
        return "seasons/view";
    }
}
