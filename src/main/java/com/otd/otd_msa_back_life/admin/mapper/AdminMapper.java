package com.otd.otd_msa_back_life.admin.mapper;

import com.otd.otd_msa_back_life.admin.model.AdminExerciseDto;
import com.otd.otd_msa_back_life.admin.model.AdminMealDataDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<AdminMealDataDto> findMealDataByUserId(Long userId);
    List<AdminExerciseDto> findExerciseDataByUserId(Long userId);
}
