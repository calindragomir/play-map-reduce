package com.example.mapreduce;

public class Main {

    public static void main(String[] args) {

        // TODO: transform this in a REST API

        // TODO: change this from static file to API call to retrieve data
        String inputFilePath = "/static/buc-baneasa-mean/TG_SOUID107503.txt";
        AverageTemperature averageTemperature = new AverageTemperature();

        // get this from query params instead of hardcoding
        Integer filteredMonth = 8;

        System.out.println(averageTemperature.calculateAvgTemperature(inputFilePath, filteredMonth));
        System.out.println("Completed");
    }

}