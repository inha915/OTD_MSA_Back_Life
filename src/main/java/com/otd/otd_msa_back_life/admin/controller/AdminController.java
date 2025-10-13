package com.otd.otd_msa_back_life.admin.controller;

import com.otd.otd_msa_back_life.admin.model.*;
import com.otd.otd_msa_back_life.admin.model.dashboard.AdminDashBoardCommunityDto;
import com.otd.otd_msa_back_life.admin.model.dashboard.AdminDashBoardExerciseDto;
import com.otd.otd_msa_back_life.admin.model.dashboard.AdminDashBoardMealDto;
import com.otd.otd_msa_back_life.admin.model.statistics.AdminStatisticsCommunityDto;
import com.otd.otd_msa_back_life.admin.model.statistics.AdminStatisticsExerciseDto;
import com.otd.otd_msa_back_life.admin.model.statistics.AdminStatisticsMealDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/meal")
    public AdminMealDto searchMeals(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return adminService.searchMeals(keyword, page, size);
    }

    @PostMapping("/meal")
    public ResponseEntity<MealFoodDb> addMeal(@RequestBody MealFoodDb meal) {
        MealFoodDb save = mealFoodDbRepository.save(meal);
        return ResponseEntity.ok().body(save);
    }

    @PutMapping("/meal/{mealId}")
    public MealFoodDb updateMeal(@PathVariable Long mealId, @RequestBody MealFoodDb food) {
        MealFoodDb mf = mealFoodDbRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 음식 ID: " + mealId));

        MealFoodDb updated = mf.toBuilder()
                .foodName(food.getFoodName())
                .flag(food.getFlag())
                .kcal(food.getKcal())
                .protein(food.getProtein())
                .carbohydrate(food.getCarbohydrate())
                .fat(food.getFat())
                .sugar(food.getSugar())
                .natrium(food.getNatrium())
                .foodCapacity(food.getFoodCapacity())
                .build();

        return mealFoodDbRepository.save(updated);
    }

    @DeleteMapping("/meal/{mealId}")
    public void deleteMeal(@PathVariable Long mealId) {
        mealFoodDbRepository.deleteById(mealId);
    }

    @DeleteMapping("/mealmake/{mealId}")
    public void deleteMealMake(@PathVariable Long mealId) {
        mealFoodMakeDbRepository.deleteById(mealId);
    }

    @GetMapping("/exercise/{userId}")
    public List<AdminExerciseDto> getExData(@PathVariable Long userId) {
        return adminService.getExerciseData(userId);
    }

    @PostMapping("/exercise")
    public ResponseEntity<?> addExercise(@RequestBody ExerciseCatalog exerciseCatalog) {
        System.out.println("받은 데이터: " + exerciseCatalog);
        ExerciseCatalog ex = exerciseCatalogRepository.save(exerciseCatalog);
        return ResponseEntity.ok().body(ex);
    }


    @GetMapping("/exercise")
    public List<ExerciseCatalog> getExercises() {
        return exerciseCatalogRepository.findAll();
    }

    @PutMapping("/exercise/{exerciseId}")
    public ExerciseCatalog updateExercise(@PathVariable Long exerciseId
            , @RequestBody ExerciseCatalog ec) {
        ExerciseCatalog ex = exerciseCatalogRepository.findByExerciseId(exerciseId);

        ExerciseCatalog updated = ex.toBuilder()
                .exerciseId(exerciseId)
                .exerciseMet(ec.getExerciseMet())
                .exerciseName(ec.getExerciseName())
                .hasDistance(ec.getHasDistance())
                .hasReps(ec.getHasReps()).build();
        return exerciseCatalogRepository.save(updated);
    }

    @DeleteMapping("/exercise/{exerciseId}")
    public void deleteExercise(@PathVariable Long exerciseId) {
        exerciseCatalogRepository.deleteById(exerciseId);
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

    // 대시보드
    @GetMapping("/dash/community")
    public AdminDashBoardCommunityDto getCommunityDashBoard() {
        return adminService.getCommunityDashBoard();
    }

    @GetMapping("/dash/exercise")
    public AdminDashBoardExerciseDto getExerciseDashBoard() {
        return adminService.getExerciseDashBoard();
    }

    @GetMapping("/dash/meal")
    public AdminDashBoardMealDto getMealDashBoard() {
        return adminService.getMealDashBoard();
    }

    // 통계
    @GetMapping("/statistics/community")
    public AdminStatisticsCommunityDto getCommunityStatistics() {
        return adminService.getCommunityStatistics();
    }

    @GetMapping("/statistics/exercise")
    public AdminStatisticsExerciseDto getExerciseStatistics() {
        return adminService.getExerciseStatistics();
    }

    @GetMapping("/statistics/meal")
    public AdminStatisticsMealDto getMealStatistics() {
        return adminService.getMealStatistics();
    }
}
