package com.project.Co2emission.controler;

import com.project.Co2emission.repository.entity.Emission;
import com.project.Co2emission.repository.entity.EmissionToCheck;
import com.project.Co2emission.service.EmissionService;
import com.project.Co2emission.service.EmissionToCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmissionViewController {

    @Autowired
    EmissionService emissionService;

    @Autowired
    EmissionToCheckService emissionToCheckService;

    @GetMapping("/addEmission")
    public String addEmissionToCheck() {
        return "addEmission";
    }

    @PostMapping("/addEmission")
    public String addEmissionToCheck(@RequestParam("countryCode") String countryCode,
                                     @RequestParam("averageEmissionValue") double averageEmissionValue,
                                     @RequestParam("localDate") String localDate) {


        EmissionToCheck emissionToCheck = new EmissionToCheck(countryCode, averageEmissionValue, localDate);
        emissionToCheckService.addEmissionToCheck(emissionToCheck);
        return "redirect:/editor ";
    }

    @GetMapping("/viewEmission")
    public String viewEmissions(Model model) {
        List<EmissionToCheck> emissions = emissionToCheckService.getAllEmissions();
        model.addAttribute("emissions", emissions);
        return "viewEmission";
    }

    @PostMapping("/viewEmission")
    public String viewEmissions(@RequestParam("countryCode") String countryCode,
                                @RequestParam("averageEmissionValue") double averageEmissionValue,
                                @RequestParam("localDate") String localDate) {

        Emission emission = new Emission(countryCode, averageEmissionValue, localDate);
        emissionService.saveEmission(emission);
        return "redirect:/emission";
    }
}
