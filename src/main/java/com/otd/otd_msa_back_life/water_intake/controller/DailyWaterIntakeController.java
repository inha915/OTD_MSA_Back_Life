package com.otd.otd_msa_back_life.water_intake.controller;


import com.otd.otd_msa_back_life.water_intake.service.DailyWaterIntakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/OTD/waterIntake")
@RequiredArgsConstructor
public class DailyWaterIntakeController {
    private final DailyWaterIntakeService DailyWaterIntakeService;
}
