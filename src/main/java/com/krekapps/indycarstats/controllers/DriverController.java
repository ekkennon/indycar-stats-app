package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.data.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.krekapps.indycarstats.IndycarStatsApplication.adminSession;

/**
 * Created by ekk on 07-Jul-17.
 */
public class DriverController {
    private final String addDriver = "Add IndyCar Driver:";

    @Autowired
    private DriverDao driverDao;

    @RequestMapping(value="drivers")
    public String driverIndex(Model model) {
        model.addAttribute("title", "IndyCar Drivers");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "drivers/index";
    }

    @RequestMapping(value="drivers/list")
    private String driversViewAll(Model model) {
        model.addAttribute("title", "IndyCar Drivers List");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("drivers", driverDao.findAll());
        return "drivers/list";
    }

    @RequestMapping(value="drivers/view/{id}")
    private String driversViewOne(Model model, @PathVariable int id) {
        Driver driver = driverDao.findOne(id);
        model.addAttribute("title", "IndyCar Driver: " + driver.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("driver", driver);
        return "drivers/view";
    }

    @RequestMapping(value="drivers/add", method= RequestMethod.GET)
    private String addDriver(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", "Unable to add Drivers without Admin privilage.");
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "index";
        }
        model.addAttribute("title", addDriver);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new Driver());
        return "drivers/add";

    }

    @RequestMapping(value="drivers/add", method=RequestMethod.POST)
    private String addDriver(Model model, @RequestParam String name, @RequestParam String loggedin, @RequestParam String twitter) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        Driver driver = new Driver(name);
        driver.setTwitterHandle(twitter);
        driverDao.save(driver);
        model.addAttribute("title", "IndyCar Stats App");
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("drivers", driverDao.findAll());
        return "redirect:";
    }
}
