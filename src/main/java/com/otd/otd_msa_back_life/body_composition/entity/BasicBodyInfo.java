package com.otd.otd_msa_back_life.body_composition.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BasicBodyInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Double height;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private Double bmi; //비만도
    @Column(nullable = false)
    private Double bmr; //기초대사량
}
