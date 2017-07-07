package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Session;
import com.krekapps.indycarstats.models.data.RaceDao;
import com.krekapps.indycarstats.models.data.SeasonDao;
import com.krekapps.indycarstats.models.data.SessionDao;
import com.krekapps.indycarstats.models.data.TrackDao;
import com.krekapps.indycarstats.models.forms.RaceAddForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by raefo on 07-Jul-17.
 */
public class RaceController {
    private final String addRace = "Add IndyCar Race:";

    @Autowired
    private RaceDao raceDao;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="races")
    public String raceIndex(Model model) {
        model.addAttribute("title", "IndyCar Races");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "races/index";
    }

    @RequestMapping(value="races/list")
    private String racesList(Model model) {
        model.addAttribute("title", "IndyCar Races List");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("races", raceDao.findAll());
        return "races/list";
    }

    @RequestMapping(value="races/view/{id}")
    private String racesViewOne(Model model, @PathVariable int id) {
        Race race = raceDao.findOne(id);
        Iterable<Session> sessions = sessionDao.findByRace(race);
        model.addAttribute("title", "IndyCar Race: " + race.getSeason().getYear() + " " + race.getTrack().getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessions);
        return "sessions/list";
    }

    @RequestMapping(value="races/add", method= RequestMethod.GET)
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
}
