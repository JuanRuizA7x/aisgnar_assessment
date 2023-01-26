package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.SchedulingRequestDTO;
import com.pragma.scheduleassessment.dto.SchedulingResponseDTO;
import com.pragma.scheduleassessment.exception.ChapterAndSpecialtyNotFoundException;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import com.pragma.scheduleassessment.repository.IChapterCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChapterCalendarService {

    private final IChapterCalendarRepository chapterCalendarRepository;

    public SchedulingResponseDTO scheduleAssessment(SchedulingRequestDTO schedulingRequest) {
        ChapterCalendarModel dataCalendar = chapterCalendarRepository.findByChapterIdAndSpecialty(schedulingRequest.getChapterId(), schedulingRequest.getSpecialty()).orElseThrow(ChapterAndSpecialtyNotFoundException::new);
        return null;
    }
}