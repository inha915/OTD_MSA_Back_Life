package com.otd.otd_msa_back_life.body_composition.mapper;

import com.otd.otd_msa_back_life.body_composition.model.BodyCompositionListGetDto;
import com.otd.otd_msa_back_life.body_composition.model.BodyCompositionListGetRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BodyCompositionMapper {

    List<BodyCompositionListGetRes> findByLimitTo(BodyCompositionListGetDto dto);
}
