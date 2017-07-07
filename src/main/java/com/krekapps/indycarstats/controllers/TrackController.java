package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Track;
import com.krekapps.indycarstats.models.data.TrackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class TrackController {
    private final String addTrack = "Add IndyCar Track:";

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="tracks")
    public String trackIndex(Model model) {
        model.addAttribute("title", "IndyCar Tracks");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "tracks/index";
    }

    @RequestMapping(value="tracks/list")
    private String tracksViewAll(Model model) {
        model.addAttribute("title", "IndyCar Tracks List");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", trackDao.findAll());
        return "tracks/list";
    }

    @RequestMapping(value="tracks/view/{id}")
    private String tracksViewOne(Model model, @PathVariable int id) {
        Track track = trackDao.findOne(id);
        List<Track> tracklist = new ArrayList<>();
        tracklist.add(track);
        Iterable<Track> t = tracklist;
        model.addAttribute("title", "IndyCar Track: " + track.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", t);
        return "tracks/view";
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
