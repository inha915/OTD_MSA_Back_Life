package com.otd.otd_msa_back_life.meal.repository;

import com.otd.otd_msa_back_life.meal.entity.MealFoodDb;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MealFoodDbRepository extends JpaRepository<MealFoodDb, Long> {

    List<MealFoodDb> findByFoodNameContaining(String foodName, Limit limit);

    Page<MealFoodDb> findByFoodNameContaining(String keyword, Pageable pageable);

    MealFoodDb findByFoodDbId(Long foodDbId);
}
