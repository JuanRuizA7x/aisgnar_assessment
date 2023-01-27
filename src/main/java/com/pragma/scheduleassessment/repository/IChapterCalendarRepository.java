package com.pragma.scheduleassessment.repository;

import com.pragma.scheduleassessment.model.ChapterCalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IChapterCalendarRepository extends JpaRepository<ChapterCalendarModel, Long> {

    Optional<ChapterCalendarModel> findByChapterIdAndSpecialty(Integer chapterId, String specialty);

}