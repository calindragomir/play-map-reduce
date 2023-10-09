package com.example.mapreduce;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileProcessor {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static Optional<Float> processLine(String line, Integer month) {

        // stream the file lines and obtain tokens with whitespaces trimmed
        List<String> tokens = Arrays.stream(line.split(","))
                .map(String::strip)
                .collect(Collectors.toList());

        if (isValidLine(tokens)) { // this is a valid line with valid content
            if (isValidMonth(tokens, month)) {
                Optional<Float> optionalTemperature = getValidTemperature(tokens.get(3));
                if (optionalTemperature.isPresent()) {
                    return Optional.of(convertToCelsius(optionalTemperature.get()));
                }
            }
        }

        return Optional.empty();
    }

    private static boolean isNumericValue(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private static boolean isValidLine(List<String> tokens) {
        return tokens.size() == 5 && isNumericValue(tokens.get(0));
    }

    private static boolean isValidMonth(List<String> tokens, Integer month) {
        String dateInformation = tokens.get(2);
        try {
            LocalDate date = LocalDate.parse(dateInformation, dateTimeFormatter);
            if (date.getMonthValue() == month) {
                return true;
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static Float convertToCelsius(Float temperature) {
        return temperature / 10;
    }

    private static Optional<Float> getValidTemperature(String temperature) {
        if (isNumericValue(temperature)) {
            Float temperatureValue = Float.parseFloat(temperature);
            if (temperatureValue == -9999) {
                return Optional.empty();
            } else {
                return Optional.of(temperatureValue);
            }
        }

        return Optional.empty();
    }
}
