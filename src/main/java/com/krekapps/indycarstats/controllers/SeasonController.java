package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
@RequestMapping(value="seasons")
public class SeasonController {
    private final String addTitle = "Add IndyCar Season:";
    private final String indexTitle = "IndyCar Seasons";
    private final String listTitle = indexTitle + " List";
    private final String singleTitle = "IndyCar Season: ";
    private final String noPrivilageTitle = "Unable to Add/Edit Seasons without Admin Privilage.";
    private final String editTitle = "Edit " + singleTitle;

    @Autowired
    private SeasonDao seasonDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", indexTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "seasons/index";
    }

    @RequestMapping(value="list")
    private String viewAll(Model model) {
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("seasons", seasonDao.findAll());
        return "seasons/list";
    }

    @RequestMapping(value="view/{id}")
    private String viewById(Model model, @PathVariable int id) {
        model.addAttribute("title", singleTitle + id);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("season", seasonDao.findOne(id));
        return "seasons/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "seasons/index";
        }

        model.addAttribute("title", addTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "seasons/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @RequestParam String loggedin) {
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

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("seasons", seasonDao.findAll());
        return "seasons/index";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    private String edit(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "seasons/index";
        }

        model.addAttribute("title", singleTitle + id);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("year", id);
        return "seasons/edit";
    }

    @RequestMapping(value="remove/{id}")
    private String remove(Model model, @PathVariable int id, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        seasonDao.delete(id);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("seasons", seasonDao.findAll());

        return "seasons/list";
    }
}
