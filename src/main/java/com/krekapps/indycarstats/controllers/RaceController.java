package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Session;
import com.krekapps.indycarstats.models.data.RaceDao;
import com.krekapps.indycarstats.models.data.SeasonDao;
import com.krekapps.indycarstats.models.data.SessionDao;
import com.krekapps.indycarstats.models.data.TrackDao;
import com.krekapps.indycarstats.models.forms.RaceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 07-Jul-17.
 */

@Controller
@RequestMapping(value="races")
public class RaceController {
    private final String addTitle = "Add IndyCar Race:";
    private final String indexTitle = "IndyCar Races";
    private final String listTitle = indexTitle + " List";
    private final String singleTitle = "IndyCar Race: ";
    private final String noPrivilageTitle = "Unable to Add/Edit Races without Admin Privilage.";
    private final String editTitle = "Edit " + singleTitle;

    @Autowired
    private RaceDao raceDao;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "IndyCar Races");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "races/index";
    }

    @RequestMapping(value="list")
    private String viewAll(Model model) {
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("races", raceDao.findAll());
        return "races/list";
    }

    @RequestMapping(value="view/{id}")
    private String viewById(Model model, @PathVariable int id) {
        Race race = raceDao.findOne(id);
        Iterable<Session> sessions = sessionDao.findByRace(race);
        model.addAttribute("title", singleTitle + race.getSeason().getYear() + " " + race.getTrack().getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("sessions", sessions);
        model.addAttribute("race", race);
        return "races/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "races/index";
        }

        RaceForm raceForm = new RaceForm(seasonDao.findAll(), trackDao.findAll(), adminSession.isSignedInString());

        model.addAttribute("title", addTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("form", raceForm);
        return "races/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid RaceForm raceForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(raceForm.getLoggedin())) {
            adminSession.setSignedIn(raceForm.getLoggedin());
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", raceForm);
            return "races/add";
        }

        Race race = new Race();
        race.setName(raceForm.getName());
        race.setSeason(seasonDao.findOne(raceForm.getYear()));
        race.setTrack(trackDao.findOne(raceForm.getTrackid()));
        raceDao.save(race);
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("races", raceDao.findAll());
        return "races/list";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    private String edit(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "sessions/index";
        }

        Race race = raceDao.findOne(id);
        RaceForm raceForm = new RaceForm(seasonDao.findAll(), trackDao.findAll(), adminSession.isSignedInString());
        raceForm.setName(race.getName());
        raceForm.setYear(race.getSeason().getYear());
        raceForm.setTrackid(race.getTrack().getId());
        raceForm.setLoggedin(adminSession.isSignedInString());

        model.addAttribute("title", editTitle + race.getName());
        model.addAttribute("form", raceForm);

        return "races/edit";
    }

    @RequestMapping(value="edit/**", method=RequestMethod.POST)
    private String edit(Model model, @ModelAttribute @Valid RaceForm raceForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(raceForm.getLoggedin())) {
            adminSession.setSignedIn(raceForm.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", editTitle + raceForm.getName());
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", raceForm);
            return "races/edit/" + raceForm.getId();
        }

        Race race = raceDao.findOne(raceForm.getId());
        race.setName(raceForm.getName());
        race.setSeason(seasonDao.findOne(raceForm.getYear()));
        race.setTrack(trackDao.findOne(raceForm.getTrackid()));

        raceDao.save(race);
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("races", raceDao.findAll());
        return "races/list";
    }

    @RequestMapping(value="remove/{id}")
    private String remove(Model model, @PathVariable int id, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        raceDao.delete(id);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("races", raceDao.findAll());
        return "races/list";
    }
}
