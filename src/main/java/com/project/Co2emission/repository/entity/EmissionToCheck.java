package com.project.Co2emission.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmissionToCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String countryCode;
    private double averageEmissionValue;
    private String localDate;

//    private Long workerId;

//    public EmissionToCheck(String countryCode, double averageEmissionValue, String localDate, Long workerId) {
//        this.countryCode = countryCode;
//        this.averageEmissionValue = averageEmissionValue;
//        this.localDate = localDate;
////        this.workerId = workerId;
//    }

    public EmissionToCheck(String countryCode, double averageEmissionValue, String localDate) {
        this.countryCode = countryCode;
        this.averageEmissionValue = averageEmissionValue;
        this.localDate = localDate;
    }

    public EmissionToCheck() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getAverageEmissionValue() {
        return averageEmissionValue;
    }

    public void setAverageEmissionValue(double averageEmissionValue) {
        this.averageEmissionValue = averageEmissionValue;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

//    public Long getWorkerId() {
//        return workerId;
//    }
//
//    public void setWorkerId(Long workerId) {
//        this.workerId = workerId;
//    }
}
