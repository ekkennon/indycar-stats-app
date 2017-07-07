package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class SeasonController {

    @Autowired
    private SeasonDao seasonDao;

    @RequestMapping(value="seasons")
    public String seasonIndex(Model model) {
        model.addAttribute("title", "IndyCar Seasons");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "seasons/index";
    }

    @RequestMapping(value="seasons/list")
    private String seasonsList(Model model) {
        model.addAttribute("title", "IndyCar Seasons List");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("seasons", seasonDao.findAll());
        return "seasons/list";
    }

    @RequestMapping(value="seasons/view/{id}")
    private String seasonsViewOne(Model model, @PathVariable int id) {
        Season season = seasonDao.findOne(id);
        model.addAttribute("title", "IndyCar Season: " + season.getYear());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("season", season);
        return "seasons/view";
    }

    @RequestMapping(value="seasons/add", method= RequestMethod.GET)
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


}
