package com.otd.otd_msa_back_life.exercise.mapper;

import com.otd.otd_msa_back_life.common.model.PagingDto;
import com.otd.otd_msa_back_life.exercise.model.AverageExerciseDurationDto;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordGetRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseRecordMapper {

    List<ExerciseRecordGetRes> findByLimitTo(PagingDto dto);
    List<AverageExerciseDurationDto> findAverageDurationGroupedByDate();
}
