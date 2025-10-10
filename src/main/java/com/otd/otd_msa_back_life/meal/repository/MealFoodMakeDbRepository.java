package com.otd.otd_msa_back_life.meal.repository;

import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import com.otd.otd_msa_back_life.meal.entity.MealFoodMakeDb;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealFoodMakeDbRepository extends JpaRepository<MealFoodMakeDb, Long> {


    MealFoodMakeDb getReferenceByFoodName(String foodName);

    MealFoodMakeDb findByUserIdAndFoodName(Long memberNoLogin, String foodName);

    List<MealFoodMakeDb> findByUserIdAndFoodNameContaining( Long userId, String foodName,Limit limit);
}
