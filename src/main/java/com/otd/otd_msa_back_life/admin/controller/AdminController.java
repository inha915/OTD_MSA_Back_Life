package com.otd.otd_msa_back_life.admin.controller;

import com.otd.otd_msa_back_life.admin.model.AdminExerciseDto;
import com.otd.otd_msa_back_life.admin.model.AdminMealDataDto;
import com.otd.otd_msa_back_life.admin.service.AdminService;
import com.otd.otd_msa_back_life.community.service.LikeService;
import com.otd.otd_msa_back_life.community.service.MentService;
import com.otd.otd_msa_back_life.community.service.PostService;
import com.otd.otd_msa_back_life.configuration.model.ResultResponse;
import com.otd.otd_msa_back_life.exercise.service.ExerciseRecordService;
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

    @GetMapping("/meal/{userId}")
    public List<AdminMealDataDto> getMealData(@PathVariable Long userId){
        return adminService.getMealData(userId);
    }

    @GetMapping("/exercise/{userId}")
    public List<AdminExerciseDto> getExData(@PathVariable Long userId) {
        return adminService.getExerciseData(userId);
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
}
