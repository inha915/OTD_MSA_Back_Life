package com.otd.otd_msa_back_life.water_intake.service;


import com.otd.otd_msa_back_life.water_intake.entity.DailyWaterIntake;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePostReq;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePutReq;
import com.otd.otd_msa_back_life.water_intake.repository.DailyWaterIntakeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyWaterIntakeService {
    private final DailyWaterIntakeRepository dailyWaterIntakeRepository;

    //    음수량 기록
    @Transactional
    public Long saveDailyWaterIntake(Long memberId, DailyWaterIntakePostReq req) {

        DailyWaterIntake dailyWaterIntake = DailyWaterIntake.builder()
                .memberId(memberId)
                .intakeDate(req.getIntakeDate())
                .amountLiter(req.getAmountLiter())
                .build();
        dailyWaterIntakeRepository.save(dailyWaterIntake);
        return dailyWaterIntake.getDailyWaterIntakeId();
    }

    //    음수량 수정
    @Transactional
    public void updateDailyWaterIntake(Long dailyWaterIntakeId, DailyWaterIntakePutReq req) {

        DailyWaterIntake dailyWaterIntake = dailyWaterIntakeRepository.findById(dailyWaterIntakeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 id 입니다."));

        dailyWaterIntake.updateAmountLiter(req.getAmountLiter());

    }
}
