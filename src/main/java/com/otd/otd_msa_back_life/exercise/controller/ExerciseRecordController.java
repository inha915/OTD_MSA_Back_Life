package com.otd.otd_msa_back_life.exercise.controller;

import com.otd.otd_msa_back_life.common.model.PagingReq;
import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordDetailGetRes;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordGetRes;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordPostReq;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordWeeklyGetReq;
import com.otd.otd_msa_back_life.exercise.service.ExerciseRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<?> saveExerciseRecord(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody ExerciseRecordPostReq req) {
        log.info("userPrincipal={}", userPrincipal);
        log.info("req:{}", req);
        Long result = exerciseRecordService.saveExerciseRecord(userPrincipal.getSignedUserId(), req);
        return ResponseEntity.ok(result);
    }

    // 페이징
    @GetMapping("/list")
    public ResponseEntity<?> getExerciseLogList(@AuthenticationPrincipal UserPrincipal userPrincipal, @ModelAttribute PagingReq req ) {
       log.info("userPrincipal:{}", userPrincipal);
        log.info("req:{}", req);
        List<ExerciseRecordGetRes> result = exerciseRecordService.getExerciseRecordList(userPrincipal.getSignedUserId(), req);
        log.info("exerciseLogList_result:{}", result);
        return ResponseEntity.ok(result);
    }

//    [GET] Weekly record
    @GetMapping("/weekly")
    public ResponseEntity<?> getExerciseRecordWeekly(@AuthenticationPrincipal UserPrincipal userPrincipal
                                                    , @ModelAttribute ExerciseRecordWeeklyGetReq req) {
        log.info("req:{}", req);
        List<ExerciseRecord> result = exerciseRecordService
                                            .getExerciseRecordWeekly(userPrincipal.getSignedUserId(), req);
        log.info("exerciseRecordWeekly_result:{}", result);
        return ResponseEntity.ok(result);
    }

//    [GET] detail
    @GetMapping("{exerciseRecordId}")
    public ResponseEntity<?> getExerciseRecord(@AuthenticationPrincipal UserPrincipal userPrincipal
                                            , @PathVariable("exerciseRecordId") Long exerciseRecordId) {
        ExerciseRecordDetailGetRes result = exerciseRecordService.getExerciseRecordDetail(userPrincipal.getSignedUserId(), exerciseRecordId);
        return ResponseEntity.ok(result);
    }

//    [DELETE]
    @DeleteMapping
    public ResponseEntity<?>  deleteExerciseRecord(@AuthenticationPrincipal UserPrincipal userPrincipal
            ,  @RequestParam("exercise_record_id") Long exerciseRecordId) {
        exerciseRecordService.deleteExerciseRecord(userPrincipal.getSignedUserId(), exerciseRecordId);
        return ResponseEntity.ok("삭제 성공");
    }
// challenge feign client
    @GetMapping("/exercise/feign")
    public int getAllExerciseRecordCount(@RequestParam Long userId,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                         LocalDate recDate){
        return exerciseRecordService.countExerciseRecordByDate(userId, recDate);
    }
}
