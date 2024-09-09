package com.project.Co2emission.controler.dto;

import java.util.List;

public class EmissionDto {


    private final double average;
    private final String end;
    private final String start;



    public EmissionDto(float average, String end, String start) {
        this.average = average;
        this.end = end;
        this.start = start;
    }

    public double getAverage() {
        return average;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }





}
