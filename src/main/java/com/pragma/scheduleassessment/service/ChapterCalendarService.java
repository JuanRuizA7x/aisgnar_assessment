package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.*;
import com.pragma.scheduleassessment.exception.ChapterAndSpecialtyNotFoundException;
import com.pragma.scheduleassessment.exception.ConsultEventClientResponseNullException;
import com.pragma.scheduleassessment.exception.EventsNotFoundException;
import com.pragma.scheduleassessment.exception.UpdateEventClientResponseNullException;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import com.pragma.scheduleassessment.repository.IChapterCalendarRepository;
import com.pragma.scheduleassessment.repository.IConsultEventClient;
import com.pragma.scheduleassessment.repository.IUpdateEventClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public SchedulingResponse scheduleAssessment(SchedulingRequest schedulingRequest) {

        String typeGetEvent = "listEvents";
        String typeUpdateEvent = "updateEvent";
        String summaryGetEvent;
        String summaryUpdateEvent;
        LocalDateTime initDate = LocalDateTime.now().withSecond(0).withNano(0);
        Integer numEvents = 1;
        List<String> emails = new ArrayList<>();

        int countEmailsRegistered;
        ChapterCalendarModel dataCalendar = chapterCalendarRepository.findByChapterIdAndSpecialty(schedulingRequest.getChapterId(), schedulingRequest.getSpecialty()).orElseThrow(ChapterAndSpecialtyNotFoundException::new);
        summaryGetEvent = dataCalendar.getNameEventInitial();
        ResponseEntity<Event> responseEvent = consultEventClient.
                getAvailableEvent(
                        typeGetEvent,
                        dataCalendar.getCalendarId(),
                        summaryGetEvent,
                        initDate,
                        numEvents);
        if (responseEvent == null) {
            throw new ConsultEventClientResponseNullException();
        }
        Event event = responseEvent.getBody();
        assert event != null;
        if (event.getItems().isEmpty()) {
            throw new EventsNotFoundException();
        }

        countEmailsRegistered = event.getItems().get(0).getAttendees().size();
        for (int i = 0; i < countEmailsRegistered; i++) {
            emails.add(event.getItems().get(0).getAttendees().get(i).getEmail());
        }
        emails.add(schedulingRequest.getEmail());
        summaryUpdateEvent = dataCalendar.getNameEventFinal() +" - " + schedulingRequest.getEmail();

        ResponseEntity<SchedulingResponse> serviceClientResponse = updateEventClient.
                updateEvent(
                        typeUpdateEvent,
                        dataCalendar.getCalendarId(),
                        event.getItems().get(0).getId(),
                        summaryUpdateEvent,
                        emails);
        if (serviceClientResponse == null) {
            throw new UpdateEventClientResponseNullException();
        }

        SchedulingResponse responseClient = serviceClientResponse.getBody();
        assert responseClient != null;
        if (responseClient.getAttendees() != null) {
            countEmailsRegistered = responseClient.getAttendees().size();
            for (int i = 0; i < countEmailsRegistered; i++) {
                if (responseClient.getAttendees().get(i).getEmail().equals(schedulingRequest.getEmail())) {
                    responseClient.getAttendees().get(i).setRole("Pragmatico Evaluado");
                } else {
                    responseClient.getAttendees().get(i).setRole("Evaluador");
                }
            }
        }
        return responseClient;

    }
}