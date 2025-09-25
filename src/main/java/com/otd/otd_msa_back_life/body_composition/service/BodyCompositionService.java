package com.otd.otd_msa_back_life.body_composition.service;

import com.otd.otd_msa_back_life.body_composition.entity.BodyComposition;
import com.otd.otd_msa_back_life.body_composition.model.LastestBodyCompositionGetRes;
import com.otd.otd_msa_back_life.body_composition.repository.BodyCompositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@ToString
@RequiredArgsConstructor
public class BodyCompositionService {
    private final BodyCompositionRepository bodyCompositionRepository;

//    최신 기록 조회
    public LastestBodyCompositionGetRes getLastestBodyComposition(Long userId) {
        BodyComposition bodyComposition = bodyCompositionRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
        LastestBodyCompositionGetRes res = LastestBodyCompositionGetRes.builder()
                .bmi(bodyComposition.getBmi())
                .cratedAt(bodyComposition.getCreatedAt())
                .PercentBodyFat(bodyComposition.getPercentBodyFat())
                .height(bodyComposition.getHeight())
                .weight(bodyComposition.getWeight())
                .skeletalMuscleMass(bodyComposition.getSkeletalMuscleMass())
                .measured_id(bodyComposition.getMeasuredId())
                .user_id(userId)
                .build();

        return res;
    }
}
