package com.otd.otd_msa_back_life.body_composition.service;

import com.otd.otd_msa_back_life.body_composition.entity.BodyComposition;
import com.otd.otd_msa_back_life.body_composition.entity.BodyCompositionMetric;
import com.otd.otd_msa_back_life.body_composition.mapper.BodyCompositionMapper;
import com.otd.otd_msa_back_life.body_composition.model.*;
import com.otd.otd_msa_back_life.body_composition.repository.BodyCompositionMetricRepository;
import com.otd.otd_msa_back_life.body_composition.repository.BodyCompositionRepository;
import com.otd.otd_msa_back_life.common.model.DateRangeDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@ToString
@RequiredArgsConstructor
public class BodyCompositionService {
    private final BodyCompositionRepository bodyCompositionRepository;
    private final BodyCompositionMetricRepository metricRepository;
    private final BodyCompositionMapper bodyCompositionMapper;

//    최신 기록 조회
    public LastestBodyCompositionGetRes getLastestBodyComposition(Long userId) {
        BodyComposition bodyComposition = bodyCompositionRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
        LastestBodyCompositionGetRes res = LastestBodyCompositionGetRes.builder()
                .bmi(bodyComposition.getBmi())
                .cratedAt(bodyComposition.getCreatedAt())
                .PercentBodyFat(bodyComposition.getPercentBodyFat())
                .height(bodyComposition.getHeight())
                .weight(bodyComposition.getWeight())
                .skeletalMuscleMass(bodyComposition.getSkeletalMuscleMass())
                .measured_id(bodyComposition.getMeasuredId())
                .user_id(userId)
                .build();

        return res;
    }

    // 체성분 변화 그래프
    public BodyCompositionSeriesGetRes getBodyCompositionSeries(Long userId, BodyCompositionSeriesGetReq req) {

        // 디바이스 타입 전체 선택하면 "All" 로 지정
        String deviceType = req.getDeviceType() != null ? req.getDeviceType() : "ALL";

        // 받아 온 날짜 범위
        DateRangeDto range;
        if (req.getRange() != null) {
            range = DateRangeDto.builder()
                    .startDate(req.getRange().getStartDate())
                    .endDate(req.getRange().getEndDate())
                    .build();
        } else {
            range = DateRangeDto.builder()
                    .startDate(LocalDateTime.now().minusDays(7))
                    .endDate(LocalDateTime.now())
                    .build();
        }

        List<BodyComposition> bodyCompositions;
        if("ALL".equalsIgnoreCase(deviceType)) {
            bodyCompositions = bodyCompositionRepository
                    .findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(
                      userId
                    , range.getStartDate()
                    , range.getEndDate()
            );
        } else {
            bodyCompositions = bodyCompositionRepository
                    .findByUserIdAndDeviceTypeAndCreatedAtBetweenOrderByCreatedAtDesc(
                              userId
                            , deviceType
                            , range.getStartDate()
                            , range.getEndDate()
                    );
        }

//        측정항목 조회 및 매핑
        List<BodyCompositionMetric> metrics = metricRepository.findAll();

        List<BodyCompositionPointDto> pointDto = bodyCompositions.stream()
                .map(item -> {
                    Map<String, Double> values = new HashMap<>();
                    for (BodyCompositionMetric metric : metrics) {
                       Double value = item.getValueByMetricCode(metric.getMetricCode());
                       values.put(metric.getMetricCode(), value);
                    }
                    return BodyCompositionPointDto.builder()
                            .date(item.getCreatedAt().toLocalDate())
                            .values(values)
                            .build();
                })
                .collect(Collectors.toList());


        BodyCompositionSeriesGetRes result = BodyCompositionSeriesGetRes.builder()
                .deviceType(deviceType)
                .points(pointDto)
                .range(range)
                .build();
        return result;
    }

    //    체성분 기록 리스트 보기
    @Transactional
    public List<BodyCompositionListGetRes> getBodyCompositionList(Long userId, BodyCompositionListGetReq req) {
        BodyCompositionListGetDto dto = BodyCompositionListGetDto.builder()
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .size(req.getRowPerPage())
                .startIdx((req.getPage() - 1) * req.getRowPerPage())
                .deviceType(req.getDeviceType())
                .userId(userId)
                .build();

        return bodyCompositionMapper.findByLimitTo(
                dto
        );
    }

}
