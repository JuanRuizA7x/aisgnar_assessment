package com.pragma.scheduleassesment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapter_calendar")
public class ChapterCalendarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_calendar_id", unique = true, nullable = false)
    private Long chapterCalendarId;
    @Column(name = "chapter_id")
    private Long chapterId;
    private String specialty;
    @Column(name = "calendar_id")
    private String calendarId;

}