package com.pragma.scheduleassessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class SchedulingRequestDTO {

    private Integer chapterId;
    private String specialty;
    private List<String> emails;

}