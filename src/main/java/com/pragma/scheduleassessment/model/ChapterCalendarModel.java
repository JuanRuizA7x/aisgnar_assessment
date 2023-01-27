package com.pragma.scheduleassessment.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapter_calendar")
public class ChapterCalendarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_calendar_id", unique = true, nullable = false)
    private Integer chapterCalendarId;
    @Column(name = "chapter_id")
    private Integer chapterId;
    @Column(name = "specialty")
    private String specialty;
    @Column(name = "calendar_id")
    private String calendarId;

}