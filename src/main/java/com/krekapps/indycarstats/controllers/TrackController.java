package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Track;
import com.krekapps.indycarstats.models.data.TrackDao;
import com.krekapps.indycarstats.models.forms.GeneralForm;
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
@RequestMapping(value="tracks")
public class TrackController {
    private final String addTitle = "Add IndyCar Track:";
    private final String indexTitle = "IndyCar Tracks";
    private final String listTitle = "IndyCar Tracks List";
    private final String singleTitle = "IndyCar Track: ";
    private final String noPrivilageTitle = "Unable to add Tracks without Admin privilage.";
    private final String editTitle = "Edit " + singleTitle;

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", indexTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "tracks/index";
    }

    @RequestMapping(value="list")
    private String viewAll(Model model) {
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/list";
    }

    @RequestMapping(value="view/{id}")
    private String viewById(Model model, @PathVariable int id) {
        Track track = trackDao.findOne(id);
        model.addAttribute("title", singleTitle + track.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("track", track);
        return "tracks/view";
    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    private String add(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "tracks/index";
        }

        model.addAttribute("title", addTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new Track());
        return "tracks/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid GeneralForm generalForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(generalForm.getLoggedin())) {
            adminSession.setSignedIn(generalForm.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", generalForm);
            return "tracks/edit/" + generalForm.getId();
        }

        Track track = new Track(generalForm.getName());
        track.setTwitterHandle(generalForm.getTwitterHandle());
        trackDao.save(track);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/list";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    private String edit(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "tracks/index";
        }

        Track track = trackDao.findOne(id);
        GeneralForm generalForm = new GeneralForm();
        generalForm.setId(id);
        generalForm.setLoggedin(adminSession.isSignedInString());
        generalForm.setName(track.getName());
        generalForm.setTwitterHandle(track.getTwitterHandle());

        model.addAttribute("form", generalForm);
        return "tracks/edit";
    }

    @RequestMapping(value="edit/**", method=RequestMethod.POST)
    private String edit(Model model, @ModelAttribute @Valid GeneralForm generalForm, Errors errors) {
        if (!adminSession.isSignedInString().equals(generalForm.getLoggedin())) {
            adminSession.setSignedIn(generalForm.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", editTitle + generalForm.getName());
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", generalForm);
            return "tracks/edit/" + generalForm.getId();
        }

        Track track = trackDao.findOne(generalForm.getId());
        track.setName(generalForm.getName());
        track.setTwitterHandle(generalForm.getTwitterHandle());
        trackDao.save(track);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", trackDao.findAll());

        return "tracks/list";
    }

    @RequestMapping(value="remove/{id}")
    private String remove(Model model, @PathVariable int id, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        trackDao.delete(id);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", trackDao.findAll());

        return "tracks/list";
    }
}
