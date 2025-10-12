package com.otd.otd_msa_back_life.admin.controller;

import com.otd.otd_msa_back_life.admin.model.*;
import com.otd.otd_msa_back_life.admin.service.AdminService;
import com.otd.otd_msa_back_life.community.repository.CommunityPostFileRepository;
import com.otd.otd_msa_back_life.community.repository.MentRepository;
import com.otd.otd_msa_back_life.community.service.LikeService;
import com.otd.otd_msa_back_life.community.service.MentService;
import com.otd.otd_msa_back_life.community.service.PostService;
import com.otd.otd_msa_back_life.configuration.model.ResultResponse;
import com.otd.otd_msa_back_life.exercise.entity.ExerciseCatalog;
import com.otd.otd_msa_back_life.exercise.repository.ExerciseCatalogRepository;
import com.otd.otd_msa_back_life.exercise.service.ExerciseRecordService;
import com.otd.otd_msa_back_life.meal.entity.MealRecordDetail;
import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import com.otd.otd_msa_back_life.meal.entity.MealFoodMakeDb;
import com.otd.otd_msa_back_life.meal.repository.MealFoodDbRepository;
import com.otd.otd_msa_back_life.meal.repository.MealFoodMakeDbRepository;
import com.otd.otd_msa_back_life.meal.service.MealService;
import com.otd.otd_msa_back_life.water_intake.service.DailyWaterIntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/OTD/admin2")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final CommunityPostFileRepository communityPostFileRepository;
    private final MentRepository mentRepository;
    private final MealFoodDbRepository mealFoodDbRepository;
    private final MealFoodMakeDbRepository mealFoodMakeDbRepository;
    private final ExerciseCatalogRepository exerciseCatalogRepository;

    @GetMapping("/community")
    public List<AdminCommunityGetRes> getCommunity() {
        return adminService.getCommunity();
    }

    @GetMapping("/meal/{userId}")
    public List<MealRecordDetail> getMealData(@PathVariable Long userId){
        return adminService.getMealData(userId);
    }

    @GetMapping("/meal/detail")
    public List<AdminMealDetailGetRes> getMealDetail(@ModelAttribute AdminMealDetailGetReq req) {
        System.out.println("req" + req);
        return adminService.getMealDetail(req);
    }

    @GetMapping("/exercise/{userId}")
    public List<AdminExerciseDto> getExData(@PathVariable Long userId) {
        return adminService.getExerciseData(userId);
    }

    @GetMapping("/meal")
    public AdminMealDto getMeals() {
        AdminMealDto dto = new AdminMealDto();
        List<MealFoodDb> mf = mealFoodDbRepository.findAll();
        List<MealFoodMakeDb> mm = mealFoodMakeDbRepository.findAll();
        dto.setMealFoodDbs(mf);
        dto.setMealFoodMakeDbs(mm);
        return dto;
    }

    @GetMapping("/exercise")
    public List<ExerciseCatalog> getExercises() {
        return exerciseCatalogRepository.findAll();
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResultResponse<?>> deleteUserData(@PathVariable Long userId) {
        try {
            adminService.removeUser(userId);

            return ResponseEntity.ok(
                    new ResultResponse<>("Life 서버: 유저 관련 데이터 삭제 성공", userId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResultResponse<>("Life 서버: 유저 관련 데이터 삭제 실패" , userId)
            );
        }
    }
    @GetMapping("/community/{postId}")
    public AdminCommunityDataDto communityData(@PathVariable Long postId) {
        return adminService.getCommunityData(postId);
    }

    @DeleteMapping("/community/{postId}")
    public ResultResponse<?> deleteCommunity(@PathVariable Long postId) {
        return adminService.removeCommunity(postId);
    }

    @DeleteMapping("/community/file/{fileId}")
    public ResultResponse<?> deleteFile(@PathVariable Long fileId) {
        communityPostFileRepository.deleteById(fileId);
        return new ResultResponse<>("파일 삭제 완료", fileId);
    }
    @DeleteMapping("/community/comment/{commentId}")
    public ResultResponse<?> deleteComment(@PathVariable Long commentId) {
        mentRepository.deleteById(commentId);
        return new ResultResponse<>("댓글 삭제 완료", commentId);
    }

}
