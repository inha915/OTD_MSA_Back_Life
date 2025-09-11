package com.otd.otd_msa_back_life.water_intake.service;


import com.otd.otd_msa_back_life.water_intake.entity.DailyWaterIntake;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePostReq;
import com.otd.otd_msa_back_life.water_intake.repository.DailyWaterIntakeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyWaterIntakeService {
    private final DailyWaterIntakeRepository dailyWaterIntakeRepository;

    public Long saveDailyWaterIntake(DailyWaterIntakePostReq req) {
        DailyWaterIntake dailyWaterIntake = DailyWaterIntake.builder()
                .amountLiter(req.getAmountLiter())
                .build();
        dailyWaterIntakeRepository.save(dailyWaterIntake);
        return dailyWaterIntake.getDailyWaterIntakeId();
    }
}
