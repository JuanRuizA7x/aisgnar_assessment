package com.pragma.scheduleassessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulingRequestDTO {
    private Integer chapterId;
    private String specialty;
    private String email;
}
