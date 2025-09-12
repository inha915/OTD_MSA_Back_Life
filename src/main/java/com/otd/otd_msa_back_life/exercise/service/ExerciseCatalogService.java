package com.otd.otd_msa_back_life.exercise.service;

import com.otd.otd_msa_back_life.exercise.model.ExerciseMetGetRes;
import com.otd.otd_msa_back_life.exercise.repository.ExerciseCatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseCatalogService {
    private final ExerciseCatalogRepository exerciseCatalogRepository;

    //    [GET] exercise
    public List<ExerciseMetGetRes> getExercises(){
        return exerciseCatalogRepository.findAll().stream()
                .map(e -> new ExerciseMetGetRes(
                        e.getExerciseId(),
                        e.getExerciseName(),
                        e.getExerciseMet()
                )).toList();

    }
}