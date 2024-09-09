package com.project.Co2emission.controler;

import com.project.Co2emission.repository.EmissionRepositoryClass;
import com.project.Co2emission.repository.entity.Emission;
import com.project.Co2emission.service.EmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;

import java.util.List;
import java.util.Map;

@RestController
public class EmissionControler {

    @Autowired
    EmissionService emissionService;

    @Autowired
    EmissionRepositoryClass emissionRepositoryClass;

    @GetMapping("/emission/{countryCode}")
    public Map<String, Double> getEmissionInformationAndSave(@PathVariable String countryCode) {
        Map<String, Double> map = emissionService.getEmissionInformationAndSave(countryCode);
        return map;
    }

    @GetMapping("/emission")
    public List<Map<String, Double>> getEmissionForFiveCountries() {
        List<Map<String, Double>> map = emissionService.getEmissionForFiveCountries();
        return map;
    }





//    @PostMapping("/addEmission")
//    public EmissionDto addEmission(@RequestBody EmissionDto emissionDto) {
//
//        return null;
//    }

}
