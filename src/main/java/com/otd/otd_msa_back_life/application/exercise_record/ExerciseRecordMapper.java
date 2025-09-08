package com.otd.otd_msa_back_life.application.exercise_record;

import com.otd.otd_msa_back_life.application.common.model.PagingDto;
import com.otd.otd_msa_back_life.application.exercise_record.model.ExerciseRecordGetRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseRecordMapper {

    List<ExerciseRecordGetRes> findByLimitTo(PagingDto dto);
}
