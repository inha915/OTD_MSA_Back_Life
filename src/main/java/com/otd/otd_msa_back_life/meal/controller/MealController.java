package com.otd.otd_msa_back_life.meal.controller;


import com.otd.otd_msa_back_life.configuration.model.JwtUser;
import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import com.otd.otd_msa_back_life.meal.model.FindFoodNameReq;
import com.otd.otd_msa_back_life.meal.model.FindFoodNameRes;
import com.otd.otd_msa_back_life.meal.service.MealService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/OTD/meal")
@Slf4j
public class MealController {
    public final MealService mealService;

    @GetMapping
    public ResponseEntity<?> getMeal(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("foodName") String foodName) {

        log.info("유저 아이디: {}", userPrincipal.getSignedUserId());
        log.info("넘어오는 값: {}", foodName);
        log.info("넘어오는 값: {}", foodName);
        List<MealFoodDb> res = mealService.findFood(foodName);
        return ResponseEntity.ok(res);

    }











    //음식 찾기 이건 내꺼
//    @GetMapping()
//    public ResponseEntity<?> findFood(HttpServletRequest httpReq, @ModelAttribute FindFoodNameReq foodInfo)
//    {
//        Integer memberId = (Integer) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
//        log.info("foodInfo: {}", foodInfo);
//
//        if (foodInfo.getFoodName() == null || foodInfo.getFoodName().isEmpty()) {
//            List<FindFoodCategoryRes> res =  mealService.findFoodCategory(foodInfo);
//
//            return ResponseEntity.ok(res);
//        }
//        else {
//            List<FindFoodNameRes> res =  mealService.findFoodName(foodInfo);
//            return ResponseEntity.ok(res);
//        }
//    }


// 아래는 강사님 feed 내용
//    @PostMapping
//    public ResultResponse<?> postFeed(@AuthenticationPrincipal UserPrincipal userPrincipal
//            , @Valid @RequestPart FeedPostReq req
//            , @RequestPart(name = "pic") List<MultipartFile> pics) {
//
//        if(pics.size() > constFile.maxPicCount) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
//                    , String.format("사진은 %d장까지 선택 가능합니다.", constFile.maxPicCount));
//        }
//        log.info("signedUserId: {}", userPrincipal.getSignedUserId());
//        log.info("req: {}", req);
//        log.info("pics.size(): {}", pics.size());
//        FeedPostRes result = feedService.postFeed(userPrincipal.getSignedUserId(), req, pics);
//        return new ResultResponse<>("피드 등록 완료", result);
//    }

}
