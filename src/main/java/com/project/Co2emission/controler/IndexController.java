package com.project.Co2emission.controler;

import com.project.Co2emission.repository.entity.Emission;
import com.project.Co2emission.service.EmissionService;
import com.project.Co2emission.service.EmissionToCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    EmissionService emissionService;

    @Autowired
    EmissionToCheckService emissionToCheckService;

    @GetMapping("/index")
    public String viewOtherEmission(Model model) {
        emissionService.clearSessionCache();
        emissionService.getEmissionForFiveCountries();
        List<Emission> emissions = emissionService.getAllEmissions();
        Double averageEmissionPL = emissionService.getAverageEmissionPl();
        Double averageEmissionDE = emissionService.getAverageEmissionDE();
        Double averageEmissionEE = emissionService.getAverageEmissionEE();
        model.addAttribute("averageEmissionPL", averageEmissionPL);
        model.addAttribute("averageEmissionDE", averageEmissionDE);
        model.addAttribute("averageEmissionEE", averageEmissionEE);
        model.addAttribute("emissions", emissions);

        return "index";
    }

    @GetMapping("/")
    public String viewAllEmissions(Model model) {
        emissionService.clearSessionCache();
        emissionService.getEmissionForFiveCountries();
        emissionService.averageEmission();

        List<Emission> emissions = emissionService.getAllEmissions();
        Double averageEmissionPL = emissionService.getAverageEmissionPl();
        Double averageEmissionDE = emissionService.getAverageEmissionDE();
        Double averageEmissionEE = emissionService.getAverageEmissionEE();
        model.addAttribute("averageEmissionPL", averageEmissionPL);
        model.addAttribute("averageEmissionDE", averageEmissionDE);
        model.addAttribute("averageEmissionEE", averageEmissionEE);
        model.addAttribute("emissions", emissions);

        return "index";
    }
}
