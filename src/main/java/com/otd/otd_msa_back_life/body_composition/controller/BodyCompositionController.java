package com.otd.otd_msa_back_life.body_composition.controller;

import com.otd.otd_msa_back_life.body_composition.model.*;
import com.otd.otd_msa_back_life.body_composition.service.BodyCompositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/OTD/body_composition")
@RequiredArgsConstructor
public class BodyCompositionController {
    private final BodyCompositionService bodyCompositionService;

//    최신 기록 조회
    @GetMapping("/lastest")
    public ResponseEntity<?> getLastestBodyComposition(@RequestParam Long userId) {
        LastestBodyCompositionGetRes result = bodyCompositionService.getLastestBodyComposition(userId);
        log.info("lastestBodyComposition:{}", result);
       return ResponseEntity.ok(result);
    }

//    체성분 변화 그래프 조회
    @GetMapping("/series")
    public ResponseEntity<?> getSeries(@RequestParam Long userId, BodyCompositionSeriesGetReq req) {
        log.info("getSeries:{}", req);
        BodyCompositionSeriesGetRes result = bodyCompositionService.getBodyCompositionSeries(userId, req);
        log.info("getSeries:{}", result);
        return ResponseEntity.ok(result);
    }

//    체성분 기록 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam Long userId, @ModelAttribute BodyCompositionListGetReq req) {
        log.info("userId:{}", userId);
        log.info("getListReq:{}", req);
        List<BodyCompositionListGetRes> result = bodyCompositionService.getBodyCompositionList(userId, req);
        log.info("getList:{}", result);
        return ResponseEntity.ok(result);
    }

}
