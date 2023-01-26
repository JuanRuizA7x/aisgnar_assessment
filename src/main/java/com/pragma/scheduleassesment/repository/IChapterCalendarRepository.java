package com.pragma.lambda.repository;

import com.pragma.lambda.model.ChapterCalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChapterCalendarRepository extends JpaRepository<ChapterCalendarModel, Long> {

    public List<ChapterCalendarModel> findByChapterIdAndSpecialty(Long chapterId, String specialty);

}