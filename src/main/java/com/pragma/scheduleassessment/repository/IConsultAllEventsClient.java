package com.pragma.scheduleassessment.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "chapter-calendar", url = "http://localhost:8080")
public interface IConsultAllEventsClient {
    @GetMapping()
    public ResponseEntity
}
