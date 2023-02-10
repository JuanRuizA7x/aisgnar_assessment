package com.pragma.scheduleassessment.repository;

import com.pragma.scheduleassessment.dto.SchedulingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "update-event", url = "${CLIENTE_FEING}")

public interface IUpdateEventClient {

    @GetMapping()
    ResponseEntity<SchedulingResponse> updateEvent(
            @RequestParam String api,
            @RequestParam String type,
            @RequestParam String calendarId,
            @RequestParam String eventId,
            @RequestParam String summary,
            @RequestParam List<String> email
    );

}