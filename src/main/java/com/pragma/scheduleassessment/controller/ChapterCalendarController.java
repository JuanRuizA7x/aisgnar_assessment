package com.pragma.scheduleassessment.controller;

import com.pragma.scheduleassessment.dto.SchedulingRequest;
import com.pragma.scheduleassessment.dto.SchedulingResponse;
import com.pragma.scheduleassessment.service.ChapterCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduleEvent")
public class ChapterCalendarController {

    private final ChapterCalendarService chapterCalendarService;

    @PostMapping()
    public ResponseEntity<SchedulingResponse> scheduleEvent(@RequestBody SchedulingRequest schedulingRequest) {
        return new ResponseEntity<>(chapterCalendarService.scheduleAssessment(schedulingRequest), HttpStatus.OK);
    }

    @GetMapping()
    public String getEvent() {
        return "Hola";
    }
}