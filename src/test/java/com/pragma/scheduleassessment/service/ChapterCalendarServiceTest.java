package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.Event;
import com.pragma.scheduleassessment.dto.Item;
import com.pragma.scheduleassessment.dto.SchedulingRequest;
import com.pragma.scheduleassessment.dto.SchedulingResponse;
import com.pragma.scheduleassessment.exception.ChapterAndSpecialtyNotFoundException;
import com.pragma.scheduleassessment.exception.ConsultEventClientResponseNullException;
import com.pragma.scheduleassessment.exception.EventsNotFoundException;
import com.pragma.scheduleassessment.exception.UpdateEventClientResponseNullException;
import com.pragma.scheduleassessment.factory.FactoryChapterCalendarDataTest;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import com.pragma.scheduleassessment.repository.IChapterCalendarRepository;
import com.pragma.scheduleassessment.repository.IConsultEventClient;
import com.pragma.scheduleassessment.repository.IUpdateEventClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;

@ExtendWith(SpringExtension.class)
class ChapterCalendarServiceTest {

    @InjectMocks
    ChapterCalendarService chapterCalendarService;
    @Mock
    IChapterCalendarRepository chapterCalendarRepository;
    @Mock
    IConsultEventClient consultEventClient;
    @Mock
    IUpdateEventClient updateEventClient;

    private SchedulingRequest schedulingRequest;
    private ResponseEntity<Event> eventResponseEntity;
    private ResponseEntity<SchedulingResponse> responseSDEntity;
    private ChapterCalendarModel chapterCalendarModel;
    private List<String> emails = new ArrayList<>();
    @BeforeEach
    void setUp() {
        emails.add("oscar.alvaradoz@pragma.com.co");
        emails.add("juan.ruiz@pragma.com.co");
        emails.add("oscar.alvaradoz@pragma.com.co");
        chapterCalendarModel = FactoryChapterCalendarDataTest.getChapterCalendarModel();
        schedulingRequest = new SchedulingRequest(1L,"Java","oscar.alvaradoz@pragma.com.co");
        SchedulingResponse response = FactoryChapterCalendarDataTest.getSchedilingResponse();
        responseSDEntity = new ResponseEntity<>(response,null,200);
        Event eventTest = FactoryChapterCalendarDataTest.getEvent();
        eventResponseEntity = new ResponseEntity<>(eventTest, null, 200);

    }

    @Test
    void mustScheduleAssessmentInCalendar() {
        String summary = chapterCalendarModel.getNameEventFinal() + " - "+ schedulingRequest.getEmail();
        when(chapterCalendarRepository.
                 findByChapterIdAndSpecialty(
                         1L,
                         "Java"
                 )).
                 thenReturn(Optional.ofNullable((chapterCalendarModel)));
         when(consultEventClient.
                 getAvailableEvent(
                         "listEvents",
                         "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                         "Sin Asignar",
                         LocalDateTime.now().withSecond(0).withNano(0),
                         1
                 )).
                 thenReturn(eventResponseEntity);
         when(updateEventClient.
                 updateEvent(
                         "updateEvent",
                         "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                         "idEvent",
                         summary,
                         emails
                 )).thenReturn(responseSDEntity);

        chapterCalendarService.scheduleAssessment(schedulingRequest);

        verify(chapterCalendarRepository).findByChapterIdAndSpecialty(1L,"Java");
        verify(consultEventClient).
                getAvailableEvent(
                        "listEvents",
                        "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                        "Sin Asignar",
                        LocalDateTime.now().withSecond(0).withNano(0),
                        1
                );
        verify(updateEventClient).updateEvent("updateEvent",
                "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                "idEvent",
                summary,
                emails
                );
    }

    @Test
    void throwChapterAndSpecialtyNotFoundExceptionWhenNotFoundDataInDb(){
        when(chapterCalendarRepository.
                findByChapterIdAndSpecialty(
                        1L,
                        "Java"
                )).
                thenReturn(Optional.empty());
        Assertions.assertThrows(ChapterAndSpecialtyNotFoundException.class,() -> chapterCalendarService.scheduleAssessment(schedulingRequest));
    }
    @Test
    void throwConsultEventClientResponseNullExceptionWhenConsultEventClientRespondNull(){
        when(chapterCalendarRepository.
                findByChapterIdAndSpecialty(
                        1L,
                        "Java"
                )).
                thenReturn(Optional.ofNullable((chapterCalendarModel)));
        when(consultEventClient.
                getAvailableEvent(
                        "listEvents",
                        "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                        "Sin Asignar",
                        LocalDateTime.now().withSecond(0).withNano(0),
                        1
                )).
                thenReturn(null);
        Assertions.assertThrows(ConsultEventClientResponseNullException.class,() -> chapterCalendarService.scheduleAssessment(schedulingRequest));
    }

    @Test
    void throwEventsNotFoundExceptionWhenItemsInObjetEventIsEmpty(){
        List<Item> items = new ArrayList<>();
        eventResponseEntity.getBody().setItems(items);
        when(chapterCalendarRepository.
                findByChapterIdAndSpecialty(
                        1L,
                        "Java"
                )).
                thenReturn(Optional.ofNullable((chapterCalendarModel)));
        when(consultEventClient.
                getAvailableEvent(
                        "listEvents",
                        "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                        "Sin Asignar",
                        LocalDateTime.now().withSecond(0).withNano(0),
                        1
                )).
                thenReturn(eventResponseEntity);
        Assertions.assertThrows(EventsNotFoundException.class,() -> chapterCalendarService.scheduleAssessment(schedulingRequest));
    }

    @Test
    void throwUpdateEventClientResponseNullExceptionWhenUpdateEventClientRespondNull(){
        when(chapterCalendarRepository.
                findByChapterIdAndSpecialty(
                        1L,
                        "Java"
                )).
                thenReturn(Optional.ofNullable((chapterCalendarModel)));
        when(consultEventClient.
                getAvailableEvent(
                        "listEvents",
                        "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                        "Sin Asignar",
                        LocalDateTime.now().withSecond(0).withNano(0),
                        1
                )).
                thenReturn(eventResponseEntity);
        when(updateEventClient.
                updateEvent(
                        "updateEvent",
                        "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                        "idEvent",
                        "Assessment",
                        emails
                )).thenReturn(null);
        Assertions.assertThrows(UpdateEventClientResponseNullException.class,() -> chapterCalendarService.scheduleAssessment(schedulingRequest));
    }
}