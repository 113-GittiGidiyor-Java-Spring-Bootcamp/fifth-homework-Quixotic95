package dev.patika.quixotic95.schoolmanagementsystem.controller;

import dev.patika.quixotic95.schoolmanagementsystem.dto.ExceptionLoggerDTO;
import dev.patika.quixotic95.schoolmanagementsystem.dto.SalaryUpdateLoggerDTO;
import dev.patika.quixotic95.schoolmanagementsystem.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoggerController {

    private final LoggerService loggerService;

    @Autowired
    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping("/exceptionLogs")
    public ResponseEntity<?> getExceptionLogs(@RequestParam String type,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "YYYY-MM-DD") String date) {
        List<ExceptionLoggerDTO> result = loggerService.findByTypeAndOrDate(type, date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/salaryUpdateLogs/{instructorId}")
    public ResponseEntity<?> getSalaryUpdateLogs(@PathVariable long instructorId,
                                                 @RequestParam(required = false) @DateTimeFormat(pattern = "YYYY-MM-DD") String date) {
        List<SalaryUpdateLoggerDTO> result = loggerService.findByInstructorIdAndOrDate(instructorId, date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
