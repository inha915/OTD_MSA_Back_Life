package com.otd.otd_msa_back_life.exercise.repository;

import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
}
