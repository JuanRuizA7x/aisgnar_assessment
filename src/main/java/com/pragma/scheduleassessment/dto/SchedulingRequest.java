package com.pragma.scheduleassessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SchedulingRequest {

    private Long chapterId;
    private String specialty;
    private String email;

}