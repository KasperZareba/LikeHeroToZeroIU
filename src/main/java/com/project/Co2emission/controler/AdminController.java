package com.project.Co2emission.controller;

import com.project.Co2emission.repository.entity.Emission;
import com.project.Co2emission.repository.entity.EmissionToCheck;
import com.project.Co2emission.service.EmissionService;
import com.project.Co2emission.service.EmissionToCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private EmissionService emissionService;

    @Autowired
    private EmissionToCheckService emissionToCheckService;

    @GetMapping
    public String adminPage(Model model) {
        List<EmissionToCheck> emissions = emissionToCheckService.getAllEmissions();
        model.addAttribute("emissions", emissions);
        return "admin";
    }

    @PostMapping("/accept/{id}")
    public String acceptEmission(@PathVariable Long id) {
        Emission emission = emissionService.getEmissionById(id);
        emissionService.saveEmission(emission);
        emissionToCheckService.deleteEmission(id);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmission(@PathVariable Long id) {
        emissionToCheckService.deleteEmission(id);
        return "redirect:/admin";
    }

//    @GetMapping("/edit/{id}")
//    public String editEmissionForm(@PathVariable Long id, Model model) {
//        Emission emission = emissionService.getEmissionById(id);
//        if (emission == null) {
//            logger.error("Emission with ID " + id + " not found.");
//            return "redirect:/admin";
//        }
//        logger.info("Editing emission with ID " + id + ": " + emission);
//        model.addAttribute("emission", emission);
//        return "editEmission"; // Nazwa szablonu Thymeleaf
//    }

//    @PostMapping("/edit")
//    public String editEmission(@ModelAttribute Emission emission) {
//        logger.info("Saving edited emission: " + emission);
//        emissionService.saveEmission(emission);
//        return "redirect:/admin";
//    }
}
