package com.pragma.scheduleassessment.repository;

import com.pragma.scheduleassessment.dto.SchedulingResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "update-event", url = "https://script.google.com/macros/s/AKfycbzBB8QzbFtLcRvIzO7qA-ntiS9hHXMvW8e3NLzVOpGuJQjmRTnnkzzZQYr7Wemk_xWV/exec")

public interface IUpdateEventClient {

    @GetMapping()
    ResponseEntity<SchedulingResponseDTO> updateEvent(
            @RequestParam String type,
            @RequestParam String calendarId,
            @RequestParam String eventId,
            @RequestParam String summary,
            @RequestParam String email
    );

}