package com.otd.otd_msa_back_life.water_intake.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(
        name = "daily_water_intake",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_daily_water_intake_user_date",
                columnNames = {"user_id", "intake_date"}
        )
)
public class DailyWaterIntake extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyWaterIntakeId;


    private Double amountLiter;

    @Column(nullable = false)
    private Long userId;


    @Column(nullable = false)
    private LocalDate intakeDate;

//    값 변경을 위한 메서드
    public void updateAmountLiter(Double amountLiter) {
        this.amountLiter = amountLiter;
    }

}
