package com.otd.otd_msa_back_life.exercise.repository;

import com.otd.otd_msa_back_life.exercise.entity.ExerciseCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseCatalogRepository extends JpaRepository<ExerciseCatalog, Long> {
    ExerciseCatalog findByExerciseId(Long exerciseId);
}
