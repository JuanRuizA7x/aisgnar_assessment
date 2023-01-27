package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.*;
import com.pragma.scheduleassessment.exception.ChapterAndSpecialtyNotFoundException;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import com.pragma.scheduleassessment.repository.IChapterCalendarRepository;
import com.pragma.scheduleassessment.repository.IConsultEventClient;
import com.pragma.scheduleassessment.repository.IUpdateEventClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChapterCalendarService {

    private final IChapterCalendarRepository chapterCalendarRepository;
    private final IConsultEventClient consultEventClient;
    private final IUpdateEventClient updateEventClient;

    public SchedulingResponseDTO scheduleAssessment(SchedulingRequestDTO schedulingRequest) {

        String typeGetEvent = "listEvents";
        String typeUpdateEvent = "updateEvent";
        String summaryGetEvent = "Sin Asignar";
        String summaryUpdateEvent = "Assessment";
        LocalDateTime initDate = LocalDateTime.now();
        Integer numEvents = 1;
        List<String> email = new ArrayList<>();

        ChapterCalendarModel dataCalendar = chapterCalendarRepository.findByChapterIdAndSpecialty(schedulingRequest.getChapterId(), schedulingRequest.getSpecialty()).orElseThrow(ChapterAndSpecialtyNotFoundException::new);
        Event event = consultEventClient.getAvailableEvent(typeGetEvent, dataCalendar.getCalendarId(), summaryGetEvent, initDate, numEvents).getBody();

        assert event != null;
        email.add(event.getItems().get(0).getAttendees().toString());
        email.add(schedulingRequest.getEmail());


        return updateEventClient.updateEvent(typeUpdateEvent, dataCalendar.getCalendarId(), event.getItems().get(0).getId(),summaryUpdateEvent, email).getBody();

    }

}