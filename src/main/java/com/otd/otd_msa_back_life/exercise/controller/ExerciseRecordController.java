package com.otd.otd_msa_back_life.exercise.controller;

import com.otd.otd_msa_back_life.common.model.PagingReq;
import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordDetailGetRes;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordGetRes;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordPostReq;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordWeeklyGetReq;
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
@RequestMapping("/api/OTD/exercise_record")
@RequiredArgsConstructor
public class ExerciseRecordController {
    private final ExerciseRecordService exerciseRecordService;

//    운동기록
    @PostMapping
    public ResponseEntity<?> saveExerciseRecord(@Valid @RequestBody ExerciseRecordPostReq req) {
        log.info("req:{}", req);
        Long result = exerciseRecordService.saveExerciseRecord(req);
        return ResponseEntity.ok(result);
    }

    // 페이징
    @GetMapping("/list")
    public ResponseEntity<?> getExerciseLogList(@RequestParam Long userId, @ModelAttribute PagingReq req ) {
        log.info("req:{}", req);
        List<ExerciseRecordGetRes> result = exerciseRecordService.getExerciseRecordList(userId, req);
        log.info("exerciseLogList_result:{}", result);
        return ResponseEntity.ok(result);
    }

//    [GET] Weekly record
    @GetMapping("/weekly")
    public ResponseEntity<?> getExerciseRecordWeekly(@RequestParam Long userId
                                                    , @ModelAttribute ExerciseRecordWeeklyGetReq req) {
        log.info("req:{}", req);
        List<ExerciseRecord> result = exerciseRecordService
                                            .getExerciseRecordWeekly(userId, req);
        log.info("exerciseRecordWeekly_result:{}", result);
        return ResponseEntity.ok(result);
    }

//    [GET] detail
    @GetMapping("{exerciseRecordId}")
    public ResponseEntity<?> getExerciseRecord(@RequestParam Long userId
                                            , @PathVariable("exerciseRecordId") Long exerciseRecordId) {
        ExerciseRecordDetailGetRes result = exerciseRecordService.getExerciseRecordDetail(userId, exerciseRecordId);
        return ResponseEntity.ok(result);
    }

//    [DELETE]
    @DeleteMapping
    public ResponseEntity<?>  deleteExerciseRecord(@RequestParam Long userId, @RequestParam Long exerciseRecordId) {
        exerciseRecordService.deleteExerciseRecord(userId, exerciseRecordId);
        return ResponseEntity.ok("삭제 성공");
    }
}
