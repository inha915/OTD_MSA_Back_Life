package com.otd.otd_msa_back_life.body_composition.repository;

import com.otd.otd_msa_back_life.body_composition.entity.BodyCompositionMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyCompositionMetricRepository extends JpaRepository<BodyCompositionMetric, Long> {
}
