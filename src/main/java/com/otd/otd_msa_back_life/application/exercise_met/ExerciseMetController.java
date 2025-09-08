package com.otd.otd_msa_back_life.application.exercise_met;


import com.otd.otd_msa_back_life.application.exercise_met.model.ExerciseMetGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/OTD/health")
@RequiredArgsConstructor
public class ExerciseMetController {
    private final ExerciseMetService exerciseMetService;

    //    [GET] exercises
    @GetMapping
    public ResponseEntity<?> getExercise() {
        List<ExerciseMetGetRes> result = exerciseMetService.getExercises();
        return ResponseEntity.ok(result);
    }
}