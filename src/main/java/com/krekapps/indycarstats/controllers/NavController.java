package com.krekapps.indycarstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class NavController {

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "IndyCar Stats App");
        return "index";
    }

    @RequestMapping(value="drivers")
    public String driverIndex(Model model) {
        model.addAttribute("title", "IndyCar Drivers");
        return "drivers/index";
    }

    @RequestMapping(value="races")
    public String raceIndex(Model model) {
        model.addAttribute("title", "IndyCar Races");
        return "races/index";
    }

    @RequestMapping(value="seasons")
    public String seasonIndex(Model model) {
        model.addAttribute("title", "IndyCar Seasons");
        return "seasons/index";
    }

    @RequestMapping(value="sessions")
    public String sessionIndex(Model model) {
        model.addAttribute("title", "IndyCar Sessions");
        return "sessions/index";
    }

    @RequestMapping(value="stats")
    public String statIndex(Model model) {
        model.addAttribute("title", "IndyCar Stats");
        return "stats/index";
    }

    @RequestMapping(value="teams")
    public String teamIndex(Model model) {
        model.addAttribute("title", "IndyCar Teams");
        return "teams/index";
    }

    @RequestMapping(value="tracks")
    public String trackIndex(Model model) {
        model.addAttribute("title", "IndyCar Tracks");
        return "tracks/index";
    }
}
