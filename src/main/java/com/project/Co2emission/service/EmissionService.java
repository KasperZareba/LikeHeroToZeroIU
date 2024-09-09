package com.project.Co2emission.service;

import com.project.Co2emission.controler.dto.EmissionDto;
import com.project.Co2emission.repository.EmissionRepositoryClass;
import com.project.Co2emission.repository.entity.Emission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmissionService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    EmissionRepositoryClass emissionRepositoryClass;

    private Map<String, Emission> sessionCache = new HashMap<>();

    boolean isInitialized = false;

    List<Double> emissionCountryPL = new ArrayList<>();
    List<Double> emissionCountryDE = new ArrayList<>();
    List<Double> emissionCountryEE = new ArrayList<>();
    double emissionPLCounter = 0;
    double emissionDECounter = 0;
    double emissionEECounter = 0;
    double sharingCoutryEmissionPl;
    double sharingCoutryEmissionDe;
    double sharingCoutryEmissionEe;

    public EmissionDto[] fetchEmissionData(String countryCode) {
        String url = "https://api.v2.emissions-api.org/api/v2/carbonmonoxide/average.json?country=" + countryCode + "&begin=2019-02-01&end=2019-02-02";
        try {
            return restTemplate.getForObject(url, EmissionDto[].class);
        } catch (RestClientException e) {
            // Logowanie błędu
            System.err.println("Błąd podczas pobierania danych dla kraju " + countryCode + ": " + e.getMessage());
            return new EmissionDto[0];
        }
    }

    public Emission createEmission(String countryCode, double average) {
        LocalDate today = LocalDate.now();
        return new Emission(countryCode, average, today.toString());
    }

    public Map<String, Double> getEmissionInformationAndSave(String countryCode) {
        if (sessionCache.containsKey(countryCode)) {
            Emission cachedEmission = sessionCache.get(countryCode);
            Map<String, Double> mapEmission = new HashMap<>();
            mapEmission.put(countryCode, cachedEmission.getAverageEmissionValue());
            return mapEmission;
        }

        EmissionDto[] emissionDto = fetchEmissionData(countryCode);
        if (emissionDto.length == 0) {
            return null;
        }

        double average = emissionDto[0].getAverage();

        Map<String, Double> mapEmission = new HashMap<>();
        mapEmission.put(countryCode, average);

        Emission emission = createEmission(countryCode, average);
        emissionRepositoryClass.createEmission(emission);

        sessionCache.put(countryCode, emission);

        return mapEmission;
    }

    public List<Map<String, Double>> getEmissionForFiveCountries() {
        if (!isInitialized) {
            String[] countryCodes = {"PL", "DE", "EE"};
            List<Map<String, Double>> list = new ArrayList<>();

            for (String code : countryCodes) {
                Map<String, Double> map = getEmissionInformationAndSave(code);
                if (map != null) {
                    list.add(map);
                }
            }
            isInitialized = true;
            return list;
        }
        return null;
    }

    public void clearSessionCache() {
        sessionCache.clear();
    }

    public void saveEmission(Emission emission) {
        System.out.println("Saving emission with param1: "
                + emission.getCountryCode() + ", param2: "
                + emission.getAverageEmissionValue() + ", countryCode: "
                + emission.getLocalDate());
        emissionRepositoryClass.createEmission(emission);
    }

    public Emission getEmissionById(Long id) {
        return emissionRepositoryClass.getEmissionById(id);
    }

    public List<Emission> getAllEmissions() {
        return emissionRepositoryClass.findAll();
    }

    public void averageEmission() {
        List<Emission> emissions = getAllEmissions();
        for (Emission emission : emissions) {
            if ("PL".equals(emission.getCountryCode())) {
                emissionCountryPL.add(emission.getAverageEmissionValue());
                emissionPLCounter = sumList(emissionCountryPL);
                double sizeListCountryPl = emissionCountryPL.size();
                sharingCoutryEmissionPl = emissionPLCounter / sizeListCountryPl;

            } else if ("DE".equals(emission.getCountryCode())) {
                emissionCountryDE.add(emission.getAverageEmissionValue());
                emissionDECounter = sumList(emissionCountryDE);
                double sizeListCountryDe = emissionCountryDE.size();
                sharingCoutryEmissionDe = emissionDECounter / sizeListCountryDe;

            } else if ("EE".equals(emission.getCountryCode())) {
                emissionCountryEE.add(emission.getAverageEmissionValue());
                emissionEECounter = sumList(emissionCountryEE);
                double sizeListCountryEe = emissionCountryEE.size();
                sharingCoutryEmissionEe = emissionEECounter / sizeListCountryEe;
            }
        }
    }

    private double sumList(List<Double> list) {
        double sum = 0.0;
        for (double num : list) {
            sum += num;
        }
        return sum;
    }

    public Double getAverageEmissionPl() {
        return sharingCoutryEmissionPl;
    }

    public Double getAverageEmissionDE() {
        return sharingCoutryEmissionDe;
    }

    public Double getAverageEmissionEE() {
        return sharingCoutryEmissionEe;
    }
}
