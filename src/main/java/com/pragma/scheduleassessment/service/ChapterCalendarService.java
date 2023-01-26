package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.Event;
import com.pragma.scheduleassessment.dto.SchedulingRequestDTO;
import com.pragma.scheduleassessment.exception.ChapterAndSpecialtyNotFoundException;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import com.pragma.scheduleassessment.repository.IChapterCalendarRepository;
import com.pragma.scheduleassessment.repository.IConsultAllEventsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ChapterCalendarService {

    private final IChapterCalendarRepository chapterCalendarRepository;
    private final IConsultAllEventsClient consultAllEventsClient;

    public Event scheduleAssessment(SchedulingRequestDTO schedulingRequest) {

        String type = "listEvents";
        String summary = "Sin Asignar";
        Date initDate = new Date();
        Integer numEvents = 1;

        ChapterCalendarModel dataCalendar = chapterCalendarRepository.findByChapterIdAndSpecialty(schedulingRequest.getChapterId(), schedulingRequest.getSpecialty()).orElseThrow(ChapterAndSpecialtyNotFoundException::new);

        Event event = consultAllEventsClient.getAllEvents(type, dataCalendar.getCalendarId(), summary, initDate, numEvents).getBody();

        return event;
    }

}