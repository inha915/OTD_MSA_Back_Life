package com.otd.otd_msa_back_life.body_composition.repository;

import com.otd.otd_msa_back_life.body_composition.entity.BodyComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BodyCompositionRepository extends JpaRepository<BodyComposition, Long> {

    BodyComposition findTopByUserIdOrderByCreatedAtDesc(Long userId);
    BodyComposition findTopByUserIdOrderByCreatedAtAsc(Long userId);
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

    @Query("""
            select b from BodyComposition b
                where b.userId = :userId
                    and FUNCTION('DATE', b.createdAt) = :day
            """)
    BodyComposition findByUserIdAndCreatedDate(
            @Param("userId") Long userId,
            @Param("day") LocalDate mealDay
    );
}
