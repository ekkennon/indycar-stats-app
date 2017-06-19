package com.krekapps.fantasyindycar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by raefo on 18-Jun-17.
 */

@Controller
@RequestMapping(value = "stats")
public class Stats {

    @RequestMapping(value = "")
    public String index() {
        return "stats/index";
    }
}
