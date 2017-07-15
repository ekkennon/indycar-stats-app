package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.*;
import com.krekapps.indycarstats.models.data.DecimalStatDao;
import com.krekapps.indycarstats.models.data.IntStatDao;
import com.krekapps.indycarstats.models.data.StringStatDao;
import com.krekapps.indycarstats.models.data.TimeStatDao;
import com.krekapps.indycarstats.models.forms.AddDataForm;
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
@RequestMapping(value="stats")
public class StatController {
    private final String addStat = "Add IndyCar Stat:";
    private String statsTitle = "IndyCar Stats List";

    @Autowired
    private DecimalStatDao decimalStatDao;

    @Autowired
    private IntStatDao intStatDao;

    @Autowired
    private StringStatDao stringStatDao;

    @Autowired
    private TimeStatDao timeStatDao;

    @RequestMapping(value="")
    public String statIndex(Model model) {
        model.addAttribute("title", "IndyCar Stats");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "stats/index";
    }

    @RequestMapping(value="decimal/list")
    private String statsDecimalViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", decimalStatDao.findAll());
        return "stats/list";
    }

    @RequestMapping(value="int/list")
    private String statsIntViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", intStatDao.findAll());
        return "stats/list";
    }

    @RequestMapping(value="string/list")
    private String statsDtringViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", stringStatDao.findAll());
        return "stats/list";
    }

    @RequestMapping(value="time/list")
    private String statsTimeViewAll(Model model) {
        model.addAttribute("title", statsTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", timeStatDao.findAll());
        return "stats/list";
    }

    @RequestMapping(value="decimal/view/{id}")
    private String statsDecimalViewOne(Model model, @PathVariable int id) {
        DecimalStat stat = decimalStatDao.findOne(id);
        List<DecimalStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<DecimalStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="int/view/{id}")
    private String statsIntViewOne(Model model, @PathVariable int id) {
        IntStat stat = intStatDao.findOne(id);
        List<IntStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<IntStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="string/view/{id}")
    private String statsStringViewOne(Model model, @PathVariable int id) {
        StringStat stat = stringStatDao.findOne(id);
        List<StringStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<StringStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="time/view/{id}")
    private String statsTimeViewOne(Model model, @PathVariable int id) {
        TimeStat stat = timeStatDao.findOne(id);
        List<TimeStat> statlist = new ArrayList<>();
        statlist.add(stat);
        Iterable<TimeStat> s = statlist;
        model.addAttribute("title", "IndyCar Stat: " + stat.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", s);
        return "stats/view";
    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    private String add(Model model, @ModelAttribute @Valid AddDataForm form, Errors errors) {
        if (!adminSession.isSignedInString().equals(form.getLoggedin())) {
            adminSession.setSignedIn(form.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "IndyCar Stats App");
            model.addAttribute("form", form);
            return "add";
        }

        List<Session> sessions;

        return "";
    }

    @RequestMapping(value="decimal/add", method= RequestMethod.GET)
    private String addDecimalStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new DecimalStat());
        return "stats/add";
    }

    @RequestMapping(value="decimal/add", method=RequestMethod.POST)
    private String addDecimalStat(Model model, @ModelAttribute @Valid DecimalStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        decimalStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", decimalStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="int/add", method=RequestMethod.GET)
    private String addIntStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new IntStat());
        return "stats/add";
    }

    @RequestMapping(value="int/add", method=RequestMethod.POST)
    private String addIntStat(Model model, @ModelAttribute @Valid IntStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        intStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", intStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="string/add", method=RequestMethod.GET)
    private String addStringStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new StringStat());
        return "stats/add";
    }

    @RequestMapping(value="string/add", method=RequestMethod.POST)
    private String addStringStat(Model model, @ModelAttribute @Valid StringStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        stringStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", stringStatDao.findAll());
        return "redirect:";
    }

    @RequestMapping(value="time/add", method=RequestMethod.GET)
    private String addTimeStat(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Stats without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }

        model.addAttribute("title", addStat);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new TimeStat());
        return "stats/add";
    }

    @RequestMapping(value="time/add", method=RequestMethod.POST)
    private String addTimeStat(Model model, @ModelAttribute @Valid TimeStat stat, Errors errors, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        if (errors.hasErrors()) {
            model.addAttribute("title", addStat);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "stats/add";
        }
        timeStatDao.save(stat);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("stats", timeStatDao.findAll());
        return "redirect:";
    }
}
