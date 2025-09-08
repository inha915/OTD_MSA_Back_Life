package com.otd.otd_msa_back_life.application.exercise_met;

import com.otd.otd_msa_back_life.application.exercise_met.model.ExerciseMetGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseMetService {
    private final ExerciseMetRepository exerciseMetRepository;

    //    [GET] exercise
    public List<ExerciseMetGetRes> getExercises(){
        return exerciseMetRepository.findAll().stream()
                .map(e -> new ExerciseMetGetRes(
                        e.getExerciseId(),
                        e.getExerciseName(),
                        e.getExerciseMet()
                )).toList();

    }
}