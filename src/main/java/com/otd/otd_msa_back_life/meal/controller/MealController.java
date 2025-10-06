package com.otd.otd_msa_back_life.meal.controller;



import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import com.otd.otd_msa_back_life.meal.entity.MealRecord;
import com.otd.otd_msa_back_life.meal.entity.MealSaveResultDto;
import com.otd.otd_msa_back_life.meal.model.FoodSearchResultDto;
import com.otd.otd_msa_back_life.meal.model.InputMealRecordReq;
import com.otd.otd_msa_back_life.meal.model.MealMainListRes;
import com.otd.otd_msa_back_life.meal.service.MealService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/OTD/meal")
@Slf4j
public class MealController {
    public final MealService mealService;

    @GetMapping("/search")
    public ResponseEntity<?> getMeal(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("foodName") String foodName) {

        log.info("유저 아이디: {}", userPrincipal.getSignedUserId());
        log.info("넘어오는 값: {}", foodName);

        List<FoodSearchResultDto> res = mealService.findFood(foodName, userPrincipal.getSignedUserId());
        return ResponseEntity.ok(res);

    }

    @PostMapping("/record")
    public ResponseEntity<?> inputMealRecord(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody InputMealRecordReq mealRecordReq) {
        log.info("유저 아이디: {}", userPrincipal.getSignedUserId());
//        mealRecordReq.setMemberNoLogin(userPrincipal.getSignedUserId());
        log.info("넘어오는 값: {}", mealRecordReq);

        MealSaveResultDto result = mealService.inputMealData(  userPrincipal.getSignedUserId(),mealRecordReq);
        log.info("결과값 : {}", result);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> inputMealRecord(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam LocalDate mealDay) {
        log.info("유저 아이디: {}", userPrincipal.getSignedUserId());
        log.info("선택 날 : {}", mealDay);

        List<MealRecord> result = mealService.mealMainListRes(userPrincipal.getSignedUserId(), mealDay);

        return ResponseEntity.ok(result);
    }
}







