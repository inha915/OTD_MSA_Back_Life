package com.otd.otd_msa_back_life.meal.service;

import com.otd.otd_msa_back_life.feign.ChallengeFeignClient;
import com.otd.otd_msa_back_life.feign.model.ExerciseDataReq;
import com.otd.otd_msa_back_life.feign.model.MealDataReq;
import com.otd.otd_msa_back_life.meal.entity.*;
import com.otd.otd_msa_back_life.meal.model.*;
import com.otd.otd_msa_back_life.meal.repository.MealFoodDbRepository;
import com.otd.otd_msa_back_life.meal.repository.MealFoodMakeDbRepository;
import com.otd.otd_msa_back_life.meal.repository.MealRecordDetailRepository;
import com.otd.otd_msa_back_life.meal.repository.MealRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Slf4j
@RequiredArgsConstructor
@Service
public class MealService {
    private final MealFoodDbRepository mealFoodDbRepository;
    private final MealFoodMakeDbRepository mealFoodMakeDbRepository;
    private final MealRecordRepository mealRecordRepository;
    private final MealRecordDetailRepository mealRecordDetailRepository;
    private final ChallengeFeignClient challengeFeignClient;

    public List<FoodSearchResultDto> findFood(String foodName , Long userId)
    {
        int limit = 20;// 검색할때 몇개 까지 받을 건지
//        List<FoodSearchResultDto> mealFoodDb = mealFoodDbRepository.findByFoodNameContaining(foodName,Limit.of(limit));
//        return mealFoodDb;
        // 시스템 음식 -> makeFoodId 는 null 로
        List<FoodSearchResultDto> mealFoodDbs = mealFoodDbRepository
                .findByFoodNameContaining(foodName, Limit.of(limit)).stream()
                .map(m -> FoodSearchResultDto.builder()
                        .foodDbId(m.getFoodDbId())
                        .foodName(m.getFoodName())
                        .flag(m.getFlag())
                        .kcal(m.getKcal())
                        .protein(m.getProtein())
                        .carbohydrate(m.getCarbohydrate())
                        .fat(m.getFat())
                        .sugar(m.getSugar())
                        .natrium(m.getNatrium())
                        .foodCapacity(m.getFoodCapacity())
                        .build()
                )
                .toList();

        // 사용자 제작 음식 -> systemFoodId 는 null 로, makeFoodId 에 userFoodId 세팅
        List<FoodSearchResultDto> userFoods = mealFoodMakeDbRepository
                .findByUserIdAndFoodNameContaining(userId, foodName,  Limit.of(limit)).stream()
                .map(u -> FoodSearchResultDto.builder()
                        .foodDbId(null)
                        .foodName(u.getFoodName())
                        .flag(u.getFlag())
                        .kcal(u.getKcal())
                        .protein(u.getProtein())
                        .carbohydrate(u.getCarbohydrate())
                        .fat(u.getFat())
                        .sugar(u.getSugar())
                        .natrium(u.getNatrium())
                        .foodCapacity(1)
                        .build()
                )
                .toList();

        // 합치고 필요하면 정렬/중복제거/최종 리밋
        return Stream.concat( userFoods.stream(), mealFoodDbs.stream())
                .limit(limit*2)
                .toList();
    }


    public MealSaveResultDto inputMealData (Long userId, InputMealRecordReq mealRecordReq)
    {
        List<Long> recordIds = new ArrayList<>();
        List<Long> newUserFoodIds = new ArrayList<>();
        int totalKcal = 0;
        float totalProtein =0;
        float totalCarbohydrate =0;
        float totalFat =0;
        float totalSugar =0;
        float totalNatrium =0;




       int result = mealRecordDetailRepository.deleteByUserIdAndMealRecordIdsMealDayAndMealRecordIdsMealTime(userId,mealRecordReq.getMealDay(),mealRecordReq.getMealTime());
       int result2 = mealRecordRepository.deleteByUserIdAndMealRecordIdsMealDayAndMealRecordIdsMealTime(userId,mealRecordReq.getMealDay(),mealRecordReq.getMealTime());

        log.info("삭제 결롱 {}  ,   {} ", result, result2);
        if (mealRecordReq.getFoods().isEmpty())
        {
            return new MealSaveResultDto(-1, null, null);
        }


        MealRecordIds mealRecordIds =
                new MealRecordIds(mealRecordReq.getMealTime(), mealRecordReq.getMealDay());
        for (InputMealRecordDetailDto listFood : mealRecordReq.getFoods()) {
            totalKcal         += listFood.getKcal();
            totalProtein      += listFood.getProtein();
            totalCarbohydrate += listFood.getCarbohydrate();
            totalFat          += listFood.getFat();
            totalSugar        += listFood.getSugar();
            totalNatrium      += listFood.getNatrium();

            MealRecord mealRecord;

            if (listFood.getFoodDbId() == null) {
                // 사용자 음식 없으면  입력
                MealFoodMakeDb userFood = mealFoodMakeDbRepository
                        .findByUserIdAndFoodName(userId, listFood.getFoodName());

                if (userFood == null) {
                    userFood = MealFoodMakeDb.builder()
                            .userId(userId)
                            .foodName(listFood.getFoodName())
                            .flag(listFood.getFlag())
                            .kcal(listFood.getKcal())
                            .protein(listFood.getProtein())
                            .carbohydrate(listFood.getCarbohydrate())
                            .fat(listFood.getFat())
                            .sugar(listFood.getSugar())
                            .natrium(listFood.getNatrium())
                            .build();

                    userFood = mealFoodMakeDbRepository.save(userFood); // 저장 후 반환값 사용 권장
                    newUserFoodIds.add(userFood.getUserFoodId());
                }

                mealRecord = MealRecord.builder()
                        .userFood(userFood)
                        .foodDb(null)
                        .mealRecordIds(mealRecordIds)
                        .userId(userId)
                        .foodAmount(listFood.getAmount())
                        .build();
              
            } else {
                // 공용 음식
                MealFoodDb foodDb = mealFoodDbRepository.findByFoodDbId(listFood.getFoodDbId());
          

                mealRecord = MealRecord.builder()
                        .foodDb(foodDb)
                        .userFood(null)
                        .mealRecordIds(mealRecordIds)
                        .userId(userId)
                        .foodAmount(listFood.getAmount())
                        .build();
            }



            MealRecord saved = mealRecordRepository.save(mealRecord);
            recordIds.add(saved.getMealId());
        }

        MealRecordDetail mealRecordDetail= MealRecordDetail.builder()
                .userId(userId)
                .mealRecordIds(mealRecordIds)
                .totalKcal(totalKcal)
                .totalProtein(totalProtein)
                .totalCarbohydrate(totalCarbohydrate)
                .totalFat(totalFat)
                .totalSugar(totalSugar)
                .totalNatrium(totalNatrium)
                .build();
        mealRecordDetail = mealRecordDetailRepository.save(mealRecordDetail);
        
        return new MealSaveResultDto( result!=0 || result2!=0  ? -1 : recordIds.size()  , recordIds, newUserFoodIds);

    }

    public List<MealRecord> mealMainListRes(Long userId , LocalDate mealDay) {

            List<MealRecord> mealRecord = mealRecordRepository.findForMain(userId,mealDay);
        log.info("<UNK> <UNK> <UNK> {} ", mealRecord);
//        List<FoodSearchResultDto> mealFoodDbs = mealFoodDbRepository
//                .findByFoodDbId(mealRecord.getFoodId()).stream()
//                .map(m -> FoodSearchResultDto.builder()
//                        .foodDbId(m.getFoodDbId())
//                        .foodName(m.getFoodName())
//                        .flag(m.getFlag())
//                        .kcal(m.getKcal())
//                        .protein(m.getProtein())
//                        .carbohydrate(m.getCarbohydrate())
//                        .fat(m.getFat())
//                        .sugar(m.getSugar())
//                        .natrium(m.getNatrium())
//                        .foodCapacity(m.getFoodCapacity())
//                        .build()
//                )
//                .toList();

        return mealRecord;
    }


//    public List<MealFoodDb> findFood(String foodName) {
//        int limit = 20;
//        String[] keywords = foodName.split("\\s+");
//
//        List<MealFoodDb> result = new ArrayList<>();
//        for (String keyword : keywords) {
//            result.addAll(mealFoodDbRepository.findByFoodNameContaining(keyword, Limit.of(limit)));
//        }
//        return result.stream()
//                .distinct()
//                .limit(limit)
//                .toList();
//    }

}
