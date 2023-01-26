package com.pragma.scheduleassessment.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "chapter-calendar", url = "http://localhost:8080")
public interface IConsultAllEvents {
}
