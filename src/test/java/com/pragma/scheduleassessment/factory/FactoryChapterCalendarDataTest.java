package com.pragma.scheduleassessment.factory;

import com.pragma.scheduleassessment.dto.*;
import com.pragma.scheduleassessment.model.ChapterCalendarModel;

import java.util.ArrayList;
import java.util.List;

public class FactoryChapterCalendarDataTest {

    public static ChapterCalendarModel getChapterCalendarModel() {
        ChapterCalendarModel chapterCalendarModel = new ChapterCalendarModel();
        chapterCalendarModel.setChapterCalendarId(1);
        chapterCalendarModel.setChapterId(1);
        chapterCalendarModel.setSpecialty("Java");
        chapterCalendarModel.setCalendarId("c_f89a637bec855ab211038b04f696e02755f5533082467b5f94cf93710922b08a@group.calendar.google.com");
        chapterCalendarModel.setNameEventInitial("Sin Asignar");
        chapterCalendarModel.setNameEventFinal("Assessment");
        return chapterCalendarModel;
    }

    public static Event getEvent() {
        Event event = new Event();
        Item item = new Item();
        List<Attendee> attendees = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        Attendee attendee1 = new Attendee("oscar.alvaradoz@pragma.com.co");
        Attendee attendee2 = new Attendee("juan.ruiz@pragma.com.co");
        attendees.add(attendee1);
        attendees.add(attendee2);
        item.setId("idEvent");
        item.setSummary("Sin Asignar");
        item.setAttendees(attendees);
        items.add(item);
        event.setItems(items);
        return event;
    }

    public static SchedulingResponseDTO getSchedilingResponse() {
        List<AttendeToSend> emails = new ArrayList<>();
        SchedulingResponseDTO response = new SchedulingResponseDTO();
        EventDate eventDate = new EventDate();
        AttendeToSend participant1 = new AttendeToSend();
        AttendeToSend participant2 = new AttendeToSend();


        eventDate.setDateTime("2023-01-04");
        eventDate.setTimeZone("America/Bogota");
        participant1.setEmail("oscar.alvaradoz@pragma.com.co");
        participant2.setEmail("adan.gonzalez@pragma.com.co");
        emails.add(participant1);
        emails.add(participant2);

        response.setSummary("Event Assement Update");
        response.setDescription("Event Assement Update");
        response.setOriginalStartTime(eventDate);
        response.setAttendees(emails);
        return response;
    }
}
