package com.example.mapreduce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AverageTemperatureTest {

    @Test
    public void testAverageTemperatureTestWorksAsExpected() {
        AverageTemperature averageTemperature = new AverageTemperature();
        float actualResult = averageTemperature.calculateAvgTemperature("/static/sampleTemperatures.txt", 8);

        assertEquals(25, actualResult);
    }
}