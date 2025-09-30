package com.otd.otd_msa_back_life.body_composition.repository;

import com.otd.otd_msa_back_life.body_composition.entity.BodyComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BodyCompositionRepository extends JpaRepository<BodyComposition, Long> {

    BodyComposition findTopByUserIdOrderByCreatedAtDesc(Long userId);
    List<BodyComposition> findByUserIdAndDeviceTypeAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long userId
            , String deviceType
            , LocalDateTime startDate
            , LocalDateTime endDate
    );
    List<BodyComposition> findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long userId
            , LocalDateTime startDate
            , LocalDateTime endDate
    );
}
