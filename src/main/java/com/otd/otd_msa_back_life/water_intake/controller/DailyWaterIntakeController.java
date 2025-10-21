package com.otd.otd_msa_back_life.water_intake.controller;


import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakeGetRes;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePostReq;
import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePutReq;
import com.otd.otd_msa_back_life.water_intake.service.DailyWaterIntakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> saveDailyWaterIntake(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody DailyWaterIntakePostReq req){


        Long result = dailyWaterIntakeService.saveDailyWaterIntake(userPrincipal.getSignedUserId(), req);
        return ResponseEntity.ok(result);
    }

//    하루 총 음수량 조회
    @GetMapping
    public ResponseEntity<?> getDailyWaterIntake(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam LocalDate intakeDate){
        DailyWaterIntakeGetRes result = dailyWaterIntakeService.getDailyWaterIntake(userPrincipal.getSignedUserId(), intakeDate);
        return ResponseEntity.ok(result);
    }

//    음수량 수정
    @PutMapping
    public ResponseEntity<?> updateDailyWaterIntake(@AuthenticationPrincipal UserPrincipal userPrincipal
                                                    , @RequestBody DailyWaterIntakePutReq req){
        log.info("dailyWaterIntakeId:{}",req.getDailyWaterIntakeId() );
        log.info("req:{}", req);
        dailyWaterIntakeService.updateDailyWaterIntake( req);
        return ResponseEntity.ok("수정성공");
    }
}
