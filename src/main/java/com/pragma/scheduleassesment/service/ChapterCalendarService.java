package com.pragma.scheduleassesment.service;

import com.pragma.scheduleassesment.dto.SchedulingRequestDTO;
import com.pragma.scheduleassesment.dto.SchedulingResponseDTO;
import com.pragma.scheduleassesment.model.ChapterCalendarModel;
import com.pragma.scheduleassesment.repository.IChapterCalendarRepository;
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