package com.otd.otd_msa_back_life.exercise.repository;

import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordGetRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
ExerciseRecord findByUserIdAndExerciseRecordId(Long userId, Long exerciseRecordId);

    Integer deleteByUserIdAndExerciseRecordId(
            Long userId,
            Long exerciseRecordId
    );

    List<ExerciseRecord> findByUserIdAndStartAtBetween(
            Long userId,
            LocalDateTime startOfWeek,
            LocalDateTime  endOfWeek
    );
}
