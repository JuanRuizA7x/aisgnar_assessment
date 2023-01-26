package com.pragma.scheduleassessment.dto;

import lombok.Data;

@Data
public class SchedulingRequestDTO {

    private Integer chapterId;
    private String specialty;
    private String email;

}