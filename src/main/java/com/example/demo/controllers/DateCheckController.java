package com.example.demo.controllers;

import com.example.demo.services.DateCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DateCheckController {
    private final DateCheckService dateCheckService;

    public DateCheckController(DateCheckService dateCheckService) {
        this.dateCheckService = dateCheckService;
    }

    @GetMapping("/checkDate/{date}")
    public boolean checkDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return this.dateCheckService.isHoliday(localDate);
        } catch (Exception e) {
            // Если дата некорректна
            System.out.println(e.getMessage());
            return false;
        }
    }

    @GetMapping("/checkDateAndTime/{dateAndTime}")
    public boolean checkDateAndTime(@PathVariable String dateAndTime) {
        return this.dateCheckService.checkDateTime(dateAndTime);
    }
}
