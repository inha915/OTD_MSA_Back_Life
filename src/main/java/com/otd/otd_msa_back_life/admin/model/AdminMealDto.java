package com.otd.otd_msa_back_life.admin.model;

import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import com.otd.otd_msa_back_life.meal.entity.MealFoodMakeDb;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminMealDto {
    private Page<MealFoodDb> mealFoodDbs;
    private List<MealFoodMakeDb> mealFoodMakeDbs;
}
