package com.dihardmg.kayrawanapp.controller;

import com.dihardmg.kayrawanapp.dao.PersonDao;
import com.dihardmg.kayrawanapp.dao.PulsaDao;
import com.dihardmg.kayrawanapp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author : Otorus
 * @since : 3/14/18
 */
@Controller
public class PersonController {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PulsaDao pulsaDao;


    @RequestMapping("/person/list")
    public String person(Model model, @PageableDefault(size = 5) Pageable pageable,
                         @RequestParam(name = "value", required = false) String value) {
        if (value != null) {
            model.addAttribute("key", value);
            model.addAttribute("data", personDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            model.addAttribute("data", personDao.findAll(pageable));
        }
        return "person/list";

    }



    @GetMapping("/person/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) Person person , Model m) {
        if (person == null) {
            person = new Person();
        }
        m.addAttribute("person", person);
        m.addAttribute("pulsa", pulsaDao.findAll());
        return "person/form";
    }


    @PostMapping("/person/form")
    public String simpan(@ModelAttribute @Valid Person person , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "person/form";
        }
        personDao.save(person);
        status.setComplete();
        return "redirect:/person/list";
    }



    @GetMapping("/person/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Person person ) {
        return new ModelMap("person", person);
    }



    @PostMapping("/person/delete")
    public Object delete(@ModelAttribute Person person , SessionStatus status) {
        try {
            personDao.delete(person);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", person.getNama())
                    .addObject("entityName", "Person")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/person/list");
        }
        status.setComplete();
        return "redirect:/person/list";
    }
}