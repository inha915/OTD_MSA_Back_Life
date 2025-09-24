package com.otd.otd_msa_back_life.meal.service;

import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import com.otd.otd_msa_back_life.meal.repository.MealFoodDbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class MealService {
    private final MealFoodDbRepository mealFoodDbRepository;

    public List<MealFoodDb> findFood(String foodName)
    {
        int limit = 20;// 검색할때 몇개 까지 받을 건지
        List<MealFoodDb> mealFoodDb = mealFoodDbRepository.findByFoodNameContaining(foodName,Limit.of(limit));
        return mealFoodDb;
    }

}
