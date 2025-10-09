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
import java.util.*;
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
        Optional<BodyComposition> optionalBodyComposition = bodyCompositionRepository.findTopByUserIdOrderByCreatedAtDesc(userId);

        if (optionalBodyComposition.isPresent()) {

            BodyComposition bodyComposition = optionalBodyComposition.get();

            LastestBodyCompositionGetRes res = LastestBodyCompositionGetRes.builder()

                    .bmi(bodyComposition.getBmi())
                    .createdAt(bodyComposition.getCreatedAt())
                    .percentBodyFat(bodyComposition.getPercentBodyFat())
                    .height(bodyComposition.getHeight())
                    .weight(bodyComposition.getWeight())
                    .skeletalMuscleMass(bodyComposition.getSkeletalMuscleMass())
                    .measuredId(bodyComposition.getMeasuredId())
                    .userId(userId)
                    .build();
            return res;
        } else {

            return LastestBodyCompositionGetRes.builder()
                    .userId(userId)
                    .bmi(0.0)
                    .percentBodyFat(0.0)
                    .height(0.0)
                    .weight(0.0)
                    .skeletalMuscleMass(0.0)
                    .build();
        }
    }

//    차트용 데이터 조회
@Transactional
public BodyCompositionSeriesGetRes getBodyCompositionSeries(Long userId, BodyCompositionSeriesGetReq req) {

    // 디바이스 타입 전체 선택하면 "All" 로 지정
    String deviceType = req.getDeviceType() != null ? req.getDeviceType() : "ALL";


    Optional<BodyComposition> optionalFirstRecord = bodyCompositionRepository
            .findTopByUserIdOrderByCreatedAtAsc(userId);
    // 받아 온 날짜 범위
    DateRangeDto range;
    List<BodyComposition> bodyCompositions;


    if (req.getRange() != null) {
        range = DateRangeDto.builder()
                .startDate(req.getRange().getStartDate())
                .endDate(req.getRange().getEndDate())
                .build();
    } else if (optionalFirstRecord.isPresent()) {

        BodyComposition firstRecord = optionalFirstRecord.get(); // Optional에서 안전하게 객체 추출
        range = DateRangeDto.builder()
                .startDate(firstRecord.getCreatedAt())
                .endDate(LocalDateTime.now())
                .build();
    } else {

        range = DateRangeDto.builder()
                .startDate(LocalDateTime.now().minusYears(1)) // 기본 시작 날짜 설정
                .endDate(LocalDateTime.now())
                .build();
    }

    if (optionalFirstRecord.isPresent() && "ALL".equalsIgnoreCase(deviceType)) {
        // 기록이 있고, ALL 타입일 때
        bodyCompositions = bodyCompositionRepository
                .findByUserIdAndCreatedAtBetweenOrderByCreatedAtAsc(
                        userId
                        , range.getStartDate()
                        , range.getEndDate()
                );
    } else if (optionalFirstRecord.isPresent()) {
        // 기록이 있고, 특정 타입일 때
        bodyCompositions = bodyCompositionRepository
                .findByUserIdAndDeviceTypeAndCreatedAtBetweenOrderByCreatedAtAsc(
                        userId
                        , deviceType
                        , range.getStartDate()
                        , range.getEndDate()
                );
    } else {
        // 기록이 없는 경우: DB 조회를 생략하고 빈 리스트를 반환
        bodyCompositions = new ArrayList<>();
    }
    // 측정항목 조회 및 매핑
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

        return bodyCompositionMapper.findByLimitTo(dto);
    }

//    [GET] metrics
    public List<BodyCompositionMetric> getMetrics() {
        List<BodyCompositionMetric> metrics = metricRepository.findAll();
    return metrics;
    }
}
