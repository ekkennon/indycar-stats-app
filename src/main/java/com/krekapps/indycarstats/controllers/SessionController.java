package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Session;
import com.krekapps.indycarstats.models.data.RaceDao;
import com.krekapps.indycarstats.models.data.SessionDao;
import com.krekapps.indycarstats.models.forms.SessionForm;
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
@RequestMapping(value="sessions")
public class SessionController {
    private final String addTitle = "Add IndyCar Session:";
    private final String indexTitle = "IndyCar Sessions";
    private final String listTitle = indexTitle + " List";
    private final String singleTitle = "IndyCar Session: ";
    private final String noPrivilageTitle = "Unable to Add/Edit Sessions without Admin Privilage.";
    private final String editTitle = "Edit " + singleTitle;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private RaceDao raceDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", indexTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "sessions/index";
    }

    @RequestMapping(value="list")
    private String viewAll(Model model) {
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessionDao.findAll());//TODO here and /races/view/{id} display race info also
        return "sessions/list";
    }

    @RequestMapping(value="view/{id}")
    private String viewById(Model model, @PathVariable int id) {
        Session session = sessionDao.findOne(id);
        model.addAttribute("title", singleTitle + session.getName());//TODO display year + trackname + sessionname
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("item", session);
        return "sessions/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "sessios/index";
        }

        SessionForm sessionForm = new SessionForm(raceDao.findAll(), adminSession.isSignedInString());

        model.addAttribute("title", addTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", sessionForm);
        return "sessions/add";
    }

    @RequestMapping(value="add/{id}", method=RequestMethod.GET)
    private String addByRace(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "sessions/index";
        }

        Race race = raceDao.findOne(id);
        SessionForm sessionForm = new SessionForm(race);

        model.addAttribute("title", addTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", sessionForm);
        return "sessions/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid SessionForm sessionForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(sessionForm.getLoggedin())) {
            adminSession.setSignedIn(sessionForm.getLoggedin());
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", sessionForm);
            return "sessions/add/" + sessionForm.getRaceid();
        }

        Session session = new Session();
        session.setName(sessionForm.getName());
        session.setRace(raceDao.findOne(sessionForm.getRaceid()));

        sessionDao.save(session);
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessionDao.findAll());
        return "sessions/list";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    private String edit(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "sessions/index";
        }

        Session session = sessionDao.findOne(id);
        SessionForm sessionForm = new SessionForm(raceDao.findAll(), adminSession.isSignedInString());
        sessionForm.setName(session.getName());
        sessionForm.setRaceid(session.getRace().getId());
        sessionForm.setId(id);
        sessionForm.setLoggedin(adminSession.isSignedInString());

        model.addAttribute("title", editTitle + session.getName());
        model.addAttribute("form", sessionForm);

        return "sessions/edit";
    }

    @RequestMapping(value="edit/**", method=RequestMethod.POST)
    private String edit(Model model, @ModelAttribute @Valid SessionForm sessionForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(sessionForm.getLoggedin())) {
            adminSession.setSignedIn(sessionForm.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", editTitle + sessionForm.getName());
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", sessionForm);
            return "sessions/edit/" + sessionForm.getId();
        }

        Session session = sessionDao.findOne(sessionForm.getId());
        session.setName(sessionForm.getName());
        session.setRace(raceDao.findOne(sessionForm.getRaceid()));

        sessionDao.save(session);
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessionDao.findAll());
        return "sessions/list";
    }

    @RequestMapping(value="remove/{id}")
    private String remove(Model model, @PathVariable int id, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        sessionDao.delete(id);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessionDao.findAll());
        return "sessions/list";
    }
}
