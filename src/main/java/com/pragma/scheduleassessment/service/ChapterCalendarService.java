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

        Attendee attendee = new Attendee(schedulingRequest.getEmail());
        List<Attendee> attendees = null;

        ChapterCalendarModel dataCalendar = chapterCalendarRepository.findByChapterIdAndSpecialty(schedulingRequest.getChapterId(), schedulingRequest.getSpecialty()).orElseThrow(ChapterAndSpecialtyNotFoundException::new);

        Event event = consultEventClient.getAvailableEvent(typeGetEvent, dataCalendar.getCalendarId(), summaryGetEvent, initDate, numEvents).getBody();

        attendees = event.getItems().get(0).getAttendees();
        attendees.add(attendee);

        Item dataUpdatedEvent = new Item();
        dataUpdatedEvent.setId(event.getItems().get(0).getId());
        dataUpdatedEvent.setSummary(summaryUpdateEvent);
        dataUpdatedEvent.setAttendees(attendees);

        return updateEventClient.updateEvent(typeUpdateEvent, dataCalendar.getCalendarId(), event.getItems().get(0).getId(), dataUpdatedEvent).getBody();

    }

}