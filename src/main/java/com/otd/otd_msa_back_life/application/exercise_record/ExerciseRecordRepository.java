package com.otd.otd_msa_back_life.application.exercise_record;

import com.otd.otd_msa_back_life.Entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
}
