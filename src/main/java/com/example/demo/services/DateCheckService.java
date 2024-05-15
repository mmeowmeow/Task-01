package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Arrays;
import java.util.List;

@Service
public class DateCheckService {
    // выходные и праздники в мае 2024
    private static final List<LocalDate> HOLIDAYS = Arrays.asList(
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 4),
            LocalDate.of(2024, 5, 5),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 5, 10),
            LocalDate.of(2024, 5, 11),
            LocalDate.of(2024, 5, 12),
            LocalDate.of(2024, 5, 18),
            LocalDate.of(2024, 5, 19),
            LocalDate.of(2024, 5, 25),
            LocalDate.of(2024, 5, 26)
    );

    public boolean isHoliday(LocalDate date) {
        return HOLIDAYS.contains(date);
    }

    public boolean checkDateTime(String dateAndTime) {
        ZonedDateTime zdtWithZoneOffset;
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateAndTime);
            zdtWithZoneOffset = localDateTime.atZone(ZoneId.systemDefault());
        } catch (Exception e) {
            // Если дата/время некорректны
            System.out.println(e.getMessage());
            return false;
        }

        LocalTime workingHoursStart = LocalTime.of(9, 0); // Начало рабочего дня
        LocalTime workingHoursEnd = LocalTime.of(18, 0); // Конец рабочего дня

        // Проверяем, что день является выходным
        if (this.isHoliday(zdtWithZoneOffset.toLocalDate())) {
            return true;
        }

        // Проверяем, что время находится в рабочих часах
        if (zdtWithZoneOffset.getHour() >= workingHoursStart.getHour() && zdtWithZoneOffset.getMinute() >= workingHoursStart.getMinute() && zdtWithZoneOffset.getHour() <= workingHoursEnd.getHour() && zdtWithZoneOffset.getMinute() <= workingHoursEnd.getMinute()) {
            return false;
        }

        return true;
    }

}
