package com.starwars.swapi.infrastructure.adapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for LocalDate operations.
 */
public class LocalDateUtils {

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTime the date-time string to parse
     * @return the parsed LocalDateTime object, or null if parsing fails
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception e) {
            return null;
        }
    }
}
