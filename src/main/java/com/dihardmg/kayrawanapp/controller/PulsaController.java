package com.dihardmg.kayrawanapp.controller;

import com.dihardmg.kayrawanapp.dao.PulsaDao;
import com.dihardmg.kayrawanapp.entity.Pulsa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author : Otorus
 * @since : 3/13/18
 */
@Controller
public class PulsaController {

    @Autowired
    private PulsaDao pd;

    @GetMapping("/pulsa/list")
    public ModelMap pulsa(@PageableDefault(size = 5) Pageable page, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("pulsa", pd.findByPaketContainingIgnoreCase(value, page));
        } else {
            return new ModelMap().addAttribute("pulsa", pd.findAll(page));
        }
    }


    @GetMapping("/pulsa/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) Pulsa pulsa  ) {
        if (pulsa == null) {
            pulsa = new Pulsa();
        }
        return new ModelMap("pulsa", pulsa);
    }




    @PostMapping("/pulsa/form")
    public String simpan(@Valid @ModelAttribute("pulsa") Pulsa pulsa  , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "pulsa/form";
        }
        pd.save(pulsa);
        status.setComplete();
        return "redirect:/pulsa/list";
    }




    @GetMapping("/pulsa/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Pulsa pulsa ) {
        return new ModelMap("pulsa", pulsa);
    }




    @PostMapping("/pulsa/delete")
    public Object delete(@ModelAttribute Pulsa pulsa , SessionStatus status) {
        try{
            pd.delete(pulsa);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", pulsa.getPaket())
                    .addObject("entityName", "Pulsa")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/pulsa/list");
        }
        status.setComplete();
        return "redirect:/pulsa/list";
    }
}
