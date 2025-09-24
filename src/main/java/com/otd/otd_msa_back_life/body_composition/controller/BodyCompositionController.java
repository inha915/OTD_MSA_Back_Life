package com.otd.otd_msa_back_life.body_composition.controller;

import com.otd.otd_msa_back_life.body_composition.entity.BodyComposition;
import com.otd.otd_msa_back_life.body_composition.service.BodyCompositionService;
import com.otd.otd_msa_back_life.configuration.model.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/OTD/body_composition")
@RequiredArgsConstructor
public class BodyCompositionController {
    private final BodyCompositionService bodyCompositionService;

    @GetMapping("/lastest")
    public ResultResponse<?> getLastestBodyComposition(@RequestParam Long userId) {


    }

}
