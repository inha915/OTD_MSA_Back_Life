package com.otd.otd_msa_back_life.meal.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class GetSummaryTotalDto {
    private final double totalCarbohydrate;
    private final double totalProtein;
    private final double totalFat;
    private final double totalSugar;
    private final double totalNatrium;
    private final double totalKcal;

}
