package com.project.Co2emission.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String countryCode;
    private double averageEmissionValue;
    private String localDate;

    public Emission(String countryCode, double avarageEmissionValue, String localDate) {
        this.countryCode = countryCode;
        this.averageEmissionValue = avarageEmissionValue;
        this.localDate = localDate;
    }

    public Emission() {

    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getAverageEmissionValue() {
        return averageEmissionValue;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setAverageEmissionValue(double averageEmissionValue) {
        this.averageEmissionValue = averageEmissionValue;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }
}
