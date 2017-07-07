package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Session;
import com.krekapps.indycarstats.models.data.RaceDao;
import com.krekapps.indycarstats.models.data.SessionDao;
import com.krekapps.indycarstats.models.forms.SessionAddForm;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SessionController {
    private final String addSession = "Add IndyCar Session:";

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private RaceDao raceDao;

    @RequestMapping(value="sessions")
    public String sessionIndex(Model model) {
        model.addAttribute("title", "IndyCar Sessions");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "sessions/index";
    }

    @RequestMapping(value="sessions/list")
    private String sessionsViewAll(Model model) {
        model.addAttribute("title", "IndyCar Sessions List");
        model.addAttribute("loggedin", adminSession.isSignedInString());
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
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", s);
        return "sessions/view";
    }

    @RequestMapping(value="sessions/add", method= RequestMethod.GET)
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
}
