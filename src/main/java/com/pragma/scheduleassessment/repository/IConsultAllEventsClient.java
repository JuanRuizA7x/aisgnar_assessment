package com.pragma.scheduleassessment.repository;

import com.pragma.scheduleassessment.dto.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@FeignClient(name = "chapter-calendar", url = "https://script.google.com/macros/s/AKfycbzBB8QzbFtLcRvIzO7qA-ntiS9hHXMvW8e3NLzVOpGuJQjmRTnnkzzZQYr7Wemk_xWV/exec")
public interface IConsultAllEventsClient {

    @GetMapping()
    public ResponseEntity<Event> getAllEvents(
            @PathVariable String type,
            @PathVariable String calendarId,
            @PathVariable String summary,
            @PathVariable Date initDate,
            @PathVariable Integer numEvents
    );

}