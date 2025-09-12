package com.otd.otd_msa_back_life.exercise.controller;


import com.otd.otd_msa_back_life.exercise.model.ExerciseMetGetRes;
import com.otd.otd_msa_back_life.exercise.service.ExerciseCatalogService;
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
public class ExerciseCatalogController {
    private final ExerciseCatalogService exerciseCatalogService;

    //    [GET] exercises
    @GetMapping
    public ResponseEntity<?> getExercise() {
        List<ExerciseMetGetRes> result = exerciseCatalogService.getExercises();
        return ResponseEntity.ok(result);
    }
}