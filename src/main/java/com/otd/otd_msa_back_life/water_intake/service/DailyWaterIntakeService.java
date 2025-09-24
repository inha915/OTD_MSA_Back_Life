package com.otd.otd_msa_back_life.water_intake.service;


import com.otd.otd_msa_back_life.water_intake.entity.DailyWaterIntake;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakeGetRes;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePostReq;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePutReq;
import com.otd.otd_msa_back_life.water_intake.repository.DailyWaterIntakeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyWaterIntakeService {
    private final DailyWaterIntakeRepository dailyWaterIntakeRepository;

    //    음수량 기록
    @Transactional
    public Long saveDailyWaterIntake(Long userId, DailyWaterIntakePostReq req) {

        DailyWaterIntake dailyWaterIntake = DailyWaterIntake.builder()
                .userId(userId)
                .intakeDate(req.getIntakeDate())
                .amountLiter(req.getAmountLiter())
                .build();
        dailyWaterIntakeRepository.save(dailyWaterIntake);
        return dailyWaterIntake.getDailyWaterIntakeId();
    }

//    하루 총 음수량 조회
    public DailyWaterIntakeGetRes getDailyWaterIntake(Long userId, LocalDate intakeDate) {
        DailyWaterIntake dailyWaterIntake = dailyWaterIntakeRepository.findByUserIdAndIntakeDate(userId, intakeDate);
        return new DailyWaterIntakeGetRes(dailyWaterIntake);

    }

    //    음수량 수정
    @Transactional
    public void updateDailyWaterIntake(Long dailyWaterIntakeId, DailyWaterIntakePutReq req) {

        DailyWaterIntake dailyWaterIntake = dailyWaterIntakeRepository.findById(dailyWaterIntakeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 id 입니다."));

        dailyWaterIntake.updateAmountLiter(req.getAmountLiter());

    }
}
