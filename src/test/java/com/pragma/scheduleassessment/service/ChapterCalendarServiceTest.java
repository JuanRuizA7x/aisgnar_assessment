package com.pragma.scheduleassessment.service;

import com.pragma.scheduleassessment.dto.Event;
import com.pragma.scheduleassessment.dto.SchedulingRequestDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @BeforeEach
    void setUp() {
        schedulingRequest = new SchedulingRequestDTO(1L,"Java","oscar.alvaradoz@pragma.com.co");
    }

    @Test
    void mustScheduleAssessmentInCalendar() {
        ChapterCalendarModel chapterCalendarModel = FactoryChapterCalendarDataTest.getChapterCalendarModel();
        Event event = FactoryChapterCalendarDataTest.getEvent();
        ResponseEntity<Event> responseEntity = new ResponseEntity<>(event, null, 200);

        when(chapterCalendarRepository.
                 findByChapterIdAndSpecialty(
                         1L,
                         "Java"
                 )).
                 thenReturn(Optional.of(chapterCalendarModel));
         when(consultEventClient.
                 getAvailableEvent(
                         "listEvents",
                         "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                         "Sin Asignar",
                         LocalDateTime.now(),
                         1
                 )).
                 thenReturn(responseEntity);

        chapterCalendarService.scheduleAssessment(schedulingRequest);

        verify(chapterCalendarRepository).findByChapterIdAndSpecialty(1L,"Java");
        verify(consultEventClient).
                getAvailableEvent(
                        "listEvents",
                        "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                        "Sin Asignar",
                        LocalDateTime.now(),
                        1
                );
        verify(updateEventClient).updateEvent("updateEvent",
                "c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com",
                "idEvent",
                "Assessment",
                List.of("oscar.alvaradoz@pragma.com.co","juan.ruiz@pragma.com.co")
                );
    }
}