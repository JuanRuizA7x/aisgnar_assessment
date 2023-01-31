package com.pragma.scheduleassessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SchedulingRequestDTO {

    private Long chapterId;
    private String specialty;
    private String email;

}