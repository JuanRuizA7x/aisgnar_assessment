package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.Event;
import com.pragma.scheduleassessment.dto.SchedulingRequestDTO;
import com.pragma.scheduleassessment.dto.SchedulingResponseDTO;
import com.pragma.scheduleassessment.exception.ConsultEventClientResponseNullException;
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

    private SchedulingRequestDTO schedulingRequest;
    private ResponseEntity<Event> eventResponseEntity;
    private ResponseEntity<SchedulingResponseDTO> responseSDEntity;
    private ChapterCalendarModel chapterCalendarModel;

    @BeforeEach
    void setUp() {
        chapterCalendarModel = FactoryChapterCalendarDataTest.getChapterCalendarModel();
        schedulingRequest = new SchedulingRequestDTO(1L,"Java","oscar.alvaradoz@pragma.com.co");
        SchedulingResponseDTO response = FactoryChapterCalendarDataTest.getSchedilingResponse();
        responseSDEntity = new ResponseEntity<>(response,null,200);
        Event eventTest = FactoryChapterCalendarDataTest.getEvent();
        eventResponseEntity = new ResponseEntity<>(eventTest, null, 200);

    }

    @Test
    void mustScheduleAssessmentInCalendar() {
        List<String> emails = new ArrayList<>();
        emails.add("oscar.alvaradoz@pragma.com.co");
        emails.add("juan.ruiz@pragma.com.co");
        emails.add("oscar.alvaradoz@pragma.com.co");

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
                "Assessment",
                emails
                );
    }

    @Test
    void trowConsultEventClientResponseNullExceptionWhenConsultEventClientRespondNull(){
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

        chapterCalendarService.scheduleAssessment(schedulingRequest);
        Assertions.assertThrows(ConsultEventClientResponseNullException.class,() -> chapterCalendarService.scheduleAssessment(schedulingRequest));
    }
}