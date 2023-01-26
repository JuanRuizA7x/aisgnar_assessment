package com.pragma.lambda.service;

import com.pragma.lambda.dto.SchedulingRequestDTO;
import com.pragma.lambda.dto.SchedulingResponseDTO;
import com.pragma.lambda.model.ChapterCalendarModel;
import com.pragma.lambda.repository.IChapterCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChapterCalendarService {

    private final IChapterCalendarRepository chapterCalendarRepository;

    public List<ChapterCalendarModel> findByChapterIdAndSpecialty(Long chapterId, String specialty) {
        return this.chapterCalendarRepository.findByChapterIdAndSpecialty(chapterId, specialty);
    }

    public SchedulingResponseDTO scheduleAssessment(SchedulingRequestDTO schedulingRequest) {
        return null;
    }

}