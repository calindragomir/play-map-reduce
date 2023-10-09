package com.example.mapreduce;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AverageTemperature {

    public float calculateAvgTemperature(String fileLocation, Integer month) {

        float averageMonthTemperature = 0;
        try {
            Path resourceFileLocation = Path.of(Objects.requireNonNull(AverageTemperature.class.getResource(fileLocation)).toURI());
            try (Stream<String> stream = Files.lines(resourceFileLocation)) {
                List<Float> filteredTemperatures = stream.map(l -> FileProcessor.processLine(l, month))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());

                float totalTemperatures = filteredTemperatures.stream()
                        .reduce((float) 0, Float::sum);

                averageMonthTemperature = totalTemperatures/filteredTemperatures.size();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return averageMonthTemperature;
    }
}
