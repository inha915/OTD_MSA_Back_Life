package com.otd.otd_msa_back_life.exercise.repository;

import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
ExerciseRecord findByUserIdAndExerciseRecordId(Long userId, Long exerciseRecordId);


    List<ExerciseRecord> findByUserIdAndStartAtBetween(
            Long userId,
            LocalDateTime startOfWeek,
            LocalDateTime endOfWeek
    );
    int countByUserIdAndStartAtBetween(Long userId, LocalDateTime start, LocalDateTime end);

    void deleteByUserIdAndExerciseRecordId(Long userId, Long exerciseRecordId);
}
