package com.otd.otd_msa_back_life.water_intake.controller;


import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakeGetRes;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePostReq;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePutReq;
import com.otd.otd_msa_back_life.water_intake.service.DailyWaterIntakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/OTD/waterIntake")
@RequiredArgsConstructor
public class DailyWaterIntakeController {
    private final DailyWaterIntakeService dailyWaterIntakeService;

//    음수량 최초 한 번 기록
    @PostMapping
    public ResponseEntity<?> saveDailyWaterIntake(@RequestBody DailyWaterIntakePostReq req, @RequestParam Long userId){
        Long result = dailyWaterIntakeService.saveDailyWaterIntake(userId, req);
        return ResponseEntity.ok(result);
    }

//    하루 총 음수량 조회
    @GetMapping
    public ResponseEntity<?> getDailyWaterIntake(@RequestParam Long userId, @RequestParam LocalDate intakeDate){
        DailyWaterIntakeGetRes result = dailyWaterIntakeService.getDailyWaterIntake(userId, intakeDate);
        return ResponseEntity.ok(result);
    }

//    음수량 수정
    @PutMapping("{dailyWaterIntakeId}")
    public ResponseEntity<?> updateDailyWaterIntake(@PathVariable Long dailyWaterIntakeId
                                                    , @RequestBody DailyWaterIntakePutReq req){
        log.info("dailyWaterIntakeId:{}", dailyWaterIntakeId);
        log.info("req:{}", req);
        dailyWaterIntakeService.updateDailyWaterIntake(dailyWaterIntakeId, req);
        return ResponseEntity.ok("수정성공");
    }
}
