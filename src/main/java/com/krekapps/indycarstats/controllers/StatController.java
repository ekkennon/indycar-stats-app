package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.*;
import com.krekapps.indycarstats.models.forms.AddDataForm;
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
@RequestMapping(value="stats")
public class StatController {
    private final String addStat = "Add IndyCar Stat:";
    private String statsTitle = "IndyCar Stats List";

    @Autowired
    private RaceDao raceDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private StatDao statDao;

    @RequestMapping(value="")
    public String statIndex(Model model) {
        model.addAttribute("title", "IndyCar Stats");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "stats/index";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid AddDataForm form, Errors errors) {
        if (!adminSession.isSignedInString().equals(form.getLoggedin())) {
            adminSession.setSignedIn(form.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "IndyCar Stats App - Add Stats");
            model.addAttribute("form", form);
            return "add";
        }

        AddDataForm addDataForm = new AddDataForm();
        addDataForm.setDriverid(form.getDriverid());
        addDataForm.setSessions(sessionDao.findByRace(raceDao.findOne(form.getRaceid())));

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("form", addDataForm);

        return "stats/addBySession";
    }

    @RequestMapping(value="adddata", method=RequestMethod.POST)
    private String addData(Model model, @ModelAttribute @Valid AddDataForm form, Errors errors) {
        if (!adminSession.isSignedInString().equals(form.getLoggedin())) {
            adminSession.setSignedIn(form.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "IndyCar Stats App - Add Stats");
            model.addAttribute("form", form);
            return "add";
        }

        AddDataForm addDataForm = new AddDataForm();
        addDataForm.setDriverid(form.getDriverid());
        addDataForm.setSessionid(form.getSessionid());

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("form", addDataForm);

        return "stats/addData";
    }

    @RequestMapping(value="submitdata", method=RequestMethod.POST)
    private String submitData(Model model, @ModelAttribute @Valid AddDataForm form, Errors errors) {
        DriverResult stat = new DriverResult();
        stat.setCarNum(form.getStat().getCarNum());
        stat.setStartPos(form.getStat().getStartPos());
        stat.setEndPos(form.getStat().getEndPos());
        stat.setStatus(form.getStat().getStatus());
        stat.setDriverid(form.getDriverid());
        stat.setSessionid(form.getSessionid());
        statDao.save(stat);

        model.addAttribute("title", "IndyCar Stats App - Add Stats");
        model.addAttribute("stats", statDao.findAll());

        return "/stats/list";
    }
}
