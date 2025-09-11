package com.otd.otd_msa_back_life.water_intake.controller;


import com.otd.otd_msa_back_life.water_intake.model.DailyWaterIntakePostReq;
import com.otd.otd_msa_back_life.water_intake.service.DailyWaterIntakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/OTD/waterIntake")
@RequiredArgsConstructor
public class DailyWaterIntakeController {
    private final DailyWaterIntakeService DailyWaterIntakeService;

    @PostMapping
    public ResponseEntity<?> saveDailyWaterIntake(@RequestBody DailyWaterIntakePostReq req){
        log.info("req:{}", req);
        Long result = DailyWaterIntakeService.saveDailyWaterIntake(req);
        log.info("result:{}", result);
        return ResponseEntity.ok(result);
    }
}
