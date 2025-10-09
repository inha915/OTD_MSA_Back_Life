package com.otd.otd_msa_back_life.exercise.repository;

import com.otd.otd_msa_back_life.body_composition.entity.BodyComposition;
import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
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


    @Query("""
            select e from ExerciseRecord e
                where e.userId = :userId
                    and FUNCTION('DATE', e.createdAt) = :day
            """)
    List<ExerciseRecord> findByUserIdAndCreatedDate(
            @Param("userId") Long userId,
            @Param("day") LocalDate mealDay
    );
}
