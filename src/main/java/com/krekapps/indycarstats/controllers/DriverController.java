package com.krekapps.indycarstats.controllers;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.Track;
import com.krekapps.indycarstats.models.data.DriverDao;
import com.krekapps.indycarstats.models.forms.GeneralForm;
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
@RequestMapping(value="drivers")
public class DriverController {
    private final String addTitle = "Add IndyCar Driver:";
    private final String indexTitle = "IndyCar Drivers";
    private final String listTitle = indexTitle + " List";
    private final String singleTitle = "IndyCar Driver: ";
    private final String noPrivilageTitle = "Unable to Add/Edit Drivers without Admin Privilage.";
    private final String editTitle = "Edit " + singleTitle;

    @Autowired
    private DriverDao driverDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", indexTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        return "drivers/index";
    }

    @RequestMapping(value="list")
    private String viewAll(Model model) {
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("drivers", driverDao.findAll());
        return "drivers/list";
    }

    @RequestMapping(value="view/{id}")
    private String viewById(Model model, @PathVariable int id) {
        Driver driver = driverDao.findOne(id);
        model.addAttribute("title", singleTitle + driver.getName());
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("driver", driver);
        return "drivers/view";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    private String add(Model model) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "drivers/index";
        }
        model.addAttribute("title", addTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute(new Driver());
        return "drivers/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    private String add(Model model, @RequestParam String name, @RequestParam String loggedin, @RequestParam String twitter) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }
        Driver driver = new Driver(name);
        driver.setTwitterHandle(twitter);
        driverDao.save(driver);
        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("drivers", driverDao.findAll());
        return "drivers/list";
    }

    @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
    private String edit(Model model, @PathVariable int id) {
        if (!adminSession.isSignedIn()) {
            model.addAttribute("title", noPrivilageTitle);
            model.addAttribute("loggedin", adminSession.isSignedInString());
            return "drivers/index";
        }

        Driver driver = driverDao.findOne(id);
        GeneralForm generalForm = new GeneralForm();
        generalForm.setId(id);
        generalForm.setLoggedin(adminSession.isSignedInString());
        generalForm.setName(driver.getName());
        generalForm.setTwitterHandle(driver.getTwitterHandle());

        model.addAttribute("form", generalForm);
        return "drivers/edit";
    }

    @RequestMapping(value="edit/**", method=RequestMethod.POST)
    private String edit(Model model, @ModelAttribute @Valid GeneralForm form, Errors errors) {
        if (!adminSession.isSignedInString().equals(form.getLoggedin())) {
            adminSession.setSignedIn(form.getLoggedin());
        }

        if (errors.hasErrors()) {
            model.addAttribute("title", editTitle + form.getName());
            model.addAttribute("loggedin", adminSession.isSignedInString());
            model.addAttribute("form", form);
            return "drivers/edit/" + form.getId();
        }

        Driver driver = driverDao.findOne(form.getId());
        driver.setName(form.getName());
        driver.setTwitterHandle(form.getTwitterHandle());
        driverDao.save(driver);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("tracks", driverDao.findAll());

        return "drivers/list";
    }

    @RequestMapping(value="remove/{id}")
    private String remove(Model model, @PathVariable int id, @RequestParam String loggedin) {
        if (!adminSession.isSignedInString().equals(loggedin)) {
            adminSession.setSignedIn(loggedin);
        }

        driverDao.delete(id);

        model.addAttribute("title", listTitle);
        model.addAttribute("loggedin", adminSession.isSignedInString());
        model.addAttribute("drivers", driverDao.findAll());
        return "drivers/list";
    }
}
