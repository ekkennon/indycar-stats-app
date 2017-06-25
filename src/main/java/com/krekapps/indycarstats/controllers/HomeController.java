package com.krekapps.indycarstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ekk on 25-Jun-17.
 */

@Controller
public class HomeController {

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "IndyCar Stats");
        return "index";
    }
}
