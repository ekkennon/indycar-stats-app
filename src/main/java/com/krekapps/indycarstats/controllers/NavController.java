package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.AdminSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class NavController {
    private AdminSession adminSession = new AdminSession(false);

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "index";
    }

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Admin Page for IndyCar Stats");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "login";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public String login(Model model, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "index";
    }

    @RequestMapping(value="add")
    public String mainAdd(Model model) {
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "add";
    }

    @RequestMapping(value="view")
    public String mainView(Model model) {
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "view";
    }

    @RequestMapping(value="drivers")
    public String driverIndex(Model model) {
        model.addAttribute("title", "IndyCar Drivers");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "drivers/index";
    }

    @RequestMapping(value="races")
    public String raceIndex(Model model) {
        model.addAttribute("title", "IndyCar Races");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "races/index";
    }

    @RequestMapping(value="seasons")
    public String seasonIndex(Model model) {
        model.addAttribute("title", "IndyCar Seasons");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "seasons/index";
    }

    @RequestMapping(value="sessions")
    public String sessionIndex(Model model) {
        model.addAttribute("title", "IndyCar Sessions");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "sessions/index";
    }

    @RequestMapping(value="stats")
    public String statIndex(Model model) {
        model.addAttribute("title", "IndyCar Stats");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "stats/index";
    }

    @RequestMapping(value="teams")
    public String teamIndex(Model model) {
        model.addAttribute("title", "IndyCar Teams");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "teams/index";
    }

    @RequestMapping(value="tracks")
    public String trackIndex(Model model) {
        model.addAttribute("title", "IndyCar Tracks");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "tracks/index";
    }
}
