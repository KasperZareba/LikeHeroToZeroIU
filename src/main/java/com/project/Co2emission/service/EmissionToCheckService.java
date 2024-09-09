package com.project.Co2emission.service;

import com.project.Co2emission.repository.EmissionToCheckRepository;
import com.project.Co2emission.repository.entity.EmissionToCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class EmissionToCheckService {

    @Autowired
    EmissionToCheckRepository emissionToCheckRepository;

    public List<EmissionToCheck> getAllEmissions() {
        return emissionToCheckRepository.findAll();
    }

    public void addEmissionToCheck(EmissionToCheck emissionToCheck) {
        System.out.println("Saving emission with param1: "
                + emissionToCheck.getCountryCode() + ", param2: "
                + emissionToCheck.getAverageEmissionValue() + ", countryCode: "
                + emissionToCheck.getLocalDate());
        emissionToCheckRepository.createEmissionToCheck(emissionToCheck);
    }

    public void acceptEmission(Long id) {
        // Implement the logic to accept the emission
        // This could involve changing a status field or moving the data to another table
        // Example:
        // EmissionToCheck emission = emissionToCheckRepository.findById(id);
        // emission.setStatus("accepted");
        // emissionToCheckRepository.update(emission);
    }

    public void deleteEmission(Long id) {
        emissionToCheckRepository.deleteById(id);
    }
}
