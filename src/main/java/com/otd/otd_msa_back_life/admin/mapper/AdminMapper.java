package com.otd.otd_msa_back_life.admin.mapper;

import com.otd.otd_msa_back_life.admin.model.*;
import com.otd.otd_msa_back_life.admin.model.dashboard.TopCommentPostRes;
import com.otd.otd_msa_back_life.admin.model.dashboard.TopLikePostRes;
import com.otd.otd_msa_back_life.admin.model.statistics.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<AdminMealDataDto> findMealDataByUserId(Long userId);
    List<AdminExerciseDto> findExerciseDataByUserId(Long userId);

    List<AdminCommunityGetRes> findAllCommunity();

    List<AdminMealDetailGetRes> findMealDetailByUserIdAndMealDayAndMealTime(AdminMealDetailGetReq req);

    List<TopLikePostRes> getTop5PostByLike();
    List<TopCommentPostRes> getTop5PostByComment();

    Double getDailyExerciseAvg();

    List<PostCountRes> countByPost();

    List<ExerciseRecordCountRes> countByExerciseRecord();
    List<ExerciseNameRecordCountRes> countByExerciseName();
    List<ExerciseDistributionCountRes> countExerciseTimeDistribution();

    List<MealRecordCountRes> countByMealRecord();
    MealMacroAverageRes getMacroAvg();
}
