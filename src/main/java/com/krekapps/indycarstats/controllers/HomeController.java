package com.krekapps.indycarstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class HomeController {
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

    @RequestMapping(value="view")
    public String mainView(Model model) {
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "view";
    }

    @RequestMapping(value="add")
    public String mainAdd(Model model) {
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "add";
    }
}
