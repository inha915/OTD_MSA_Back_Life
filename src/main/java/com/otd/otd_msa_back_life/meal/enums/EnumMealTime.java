package com.otd.otd_msa_back_life.meal.enums;

import com.otd.otd_msa_back_life.configuration.enumcode.AbstractEnumCodeConverter;
import com.otd.otd_msa_back_life.configuration.enumcode.EnumMapperType;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumMealTime implements EnumMapperType {
    BREAKFAST("01", "아침"),
    LUNCH("02", "점심"),
    DINNER("03", "저녁"),
    SNACK("04", "간식");

    private final String code;
    private final String value;

    @Converter(autoApply = true)
    public static class CodeConverter extends AbstractEnumCodeConverter<EnumMealTime> {
        public CodeConverter() {
            super(EnumMealTime.class, false);
        }
    }
}
