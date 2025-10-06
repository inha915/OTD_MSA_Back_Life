package com.otd.otd_msa_back_life.exercise.repository;

import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import com.otd.otd_msa_back_life.feign.model.ExerciseCountAndSum;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
ExerciseRecord findByUserIdAndExerciseRecordId(Long userId, Long exerciseRecordId);


    List<ExerciseRecord> findByUserIdAndStartAtBetween(
            Long userId,
            LocalDateTime startOfWeek,
            LocalDateTime endOfWeek
    );

    @Query("SELECT COUNT(er) AS count, COALESCE(SUM(er.activityKcal), 0) AS totalKcal " +
            "FROM ExerciseRecord er " +
            "WHERE er.userId = :userId " +
            "AND er.startAt BETWEEN :start AND :end")
    ExerciseCountAndSum getDailyExerciseSummary(@Param("userId") Long userId,
                                                @Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end);

    void deleteByUserIdAndExerciseRecordId(Long userId, Long exerciseRecordId);
}
