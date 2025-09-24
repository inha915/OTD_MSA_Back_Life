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

    public LastestBodyCompositionGetRes getLastestBodyComposition(Long userId) {
        BodyComposition bodyComposition = bodyCompositionRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
        LastestBodyCompositionGetRes res = LastestBodyCompositionGetRes.builder()
                .Bmi(bodyComposition.getBmi())
                .cratedAt(LocalDateTime.now())


                .build();
        return null;
    }
}
