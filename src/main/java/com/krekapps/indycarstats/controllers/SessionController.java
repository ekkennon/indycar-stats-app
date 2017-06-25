package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Session;
import com.krekapps.indycarstats.models.data.TrackDao;
import com.krekapps.indycarstats.models.data.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
@RequestMapping(value="sessions")
public class SessionController {
    private String addTitle = "Add IndyCar Track:";
    private String viewListTitle = "IndyCar Track Sessions";

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private TrackDao trackDao;

    @RequestMapping(value="")
    private String index(Model model) {
        model.addAttribute("title", viewListTitle);
        return "sessions/index";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("title", addTitle);
        model.addAttribute(new Session());
        model.addAttribute("tracks", trackDao.findAll());
        return "sessions/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid Session session, Errors errors, @RequestParam int trackId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", addTitle);
            return "sessions/add";
        }

        sessionDao.save(session);
        model.addAttribute("title", viewListTitle);
        model.addAttribute("tracks", sessionDao.findAll());
        return "redirect:";
    }
}
