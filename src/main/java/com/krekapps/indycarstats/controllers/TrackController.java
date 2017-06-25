package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Track;
import com.krekapps.indycarstats.models.data.TrackDao;
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
@RequestMapping(value="tracks")
public class TrackController {
    private String addTitle = "Add IndyCar Track:";
    private String viewListTitle = "IndyCar Races List";

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="")
    private String index(Model model) {
        model.addAttribute("title", "IndyCar Tracks");
        return "tracks/index";
    }

    @RequestMapping(value="view")
    private String viewAll(Model model) {
        model.addAttribute("title", viewListTitle);
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/view";
    }

    @RequestMapping(value="view/{id}")
    private String viewOne(Model model, @PathVariable int id) {
        Track track = trackDao.findOne(id);
        ArrayList<Track> tracklist = new ArrayList<>();
        tracklist.add(track);
        Iterable<Track> t = tracklist;
        model.addAttribute("title", "IndyCar Track: " + track.getName());
        model.addAttribute("tracks", t);
        return "tracks/view";
    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("title", addTitle);
        model.addAttribute(new Track());
        return "tracks/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid Track track, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            return "tracks/add";
        }

        trackDao.save(track);
        model.addAttribute("title", viewListTitle);
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/view";
    }
}
