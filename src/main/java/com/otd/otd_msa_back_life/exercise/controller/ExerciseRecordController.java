package com.otd.otd_msa_back_life.exercise.controller;

import com.otd.otd_msa_back_life.common.model.PagingReq;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordGetRes;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordPostReq;
import com.otd.otd_msa_back_life.exercise.service.ExerciseRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/OTD/health")
@RequiredArgsConstructor
public class ExerciseRecordController {

    private final ExerciseRecordService exerciseRecordService;

    @PostMapping("/elog")
    public ResponseEntity<?> saveExerciseRecord(@Valid @RequestBody ExerciseRecordPostReq req) {
        log.info("req:{}", req);

        Long result = exerciseRecordService.saveExerciseRecord(req);
        return ResponseEntity.ok(result);

    }

    // 페이징
    @GetMapping("/elog/list")
    public ResponseEntity<?> getExerciseLogList(@ModelAttribute PagingReq req) {
        log.info("req:{}", req);
        List<ExerciseRecordGetRes> result = exerciseRecordService.getExerciseLogList(req);
        log.info("exerciseLogList_result:{}", result);
        return ResponseEntity.ok(result);

    }
}
