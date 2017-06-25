package com.krekapps.indycarstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
@RequestMapping(value="races")
public class RaceController {

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "IndyCar Races");
        return "races/index";
    }
}
