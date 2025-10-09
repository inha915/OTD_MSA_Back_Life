package com.otd.otd_msa_back_life.body_composition.repository;

import com.otd.otd_msa_back_life.body_composition.entity.BodyComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BodyCompositionRepository extends JpaRepository<BodyComposition, Long> {
    Optional<BodyComposition> findTopByUserIdOrderByCreatedAtDesc(Long userId);
    Optional<BodyComposition> findTopByUserIdOrderByCreatedAtAsc(Long userId);
    List<BodyComposition> findByUserIdAndDeviceTypeAndCreatedAtBetweenOrderByCreatedAtAsc(
            Long userId
            , String deviceType
            , LocalDateTime startDate
            , LocalDateTime endDate
    );
    List<BodyComposition> findByUserIdAndCreatedAtBetweenOrderByCreatedAtAsc(
            Long userId
            , LocalDateTime startDate
            , LocalDateTime endDate
    );
}
