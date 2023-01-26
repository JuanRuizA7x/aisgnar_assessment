package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.SchedulingRequestDTO;
import com.pragma.scheduleassessment.dto.SchedulingResponseDTO;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import com.pragma.scheduleassessment.repository.IChapterCalendarRepository;
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