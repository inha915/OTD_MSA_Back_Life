package com.otd.otd_msa_back_life.exercise.service;

import com.otd.otd_msa_back_life.exercise.entity.ExerciseCatalog;
import com.otd.otd_msa_back_life.exercise.entity.ExerciseRecord;
import com.otd.otd_msa_back_life.common.model.PagingDto;
import com.otd.otd_msa_back_life.common.model.PagingReq;
import com.otd.otd_msa_back_life.exercise.repository.ExerciseCatalogRepository;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordGetRes;
import com.otd.otd_msa_back_life.exercise.model.ExerciseRecordPostReq;
import com.otd.otd_msa_back_life.exercise.mapper.ExerciseRecordMapper;
import com.otd.otd_msa_back_life.exercise.repository.ExerciseRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseRecordService {
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ExerciseCatalogRepository exerciseMetRepository;
    private final ExerciseRecordMapper exerciseRecordMapper;

    //    [post] exerciseRecord
    public Long saveExerciseRecord(ExerciseRecordPostReq req) {
//        exercise 존재 여부 확인
        ExerciseCatalog exerciseId = exerciseMetRepository.findById(req.getExerciseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 운동입니다."));

//                exerciseRecord 생성
        ExerciseRecord exerciseRecord = ExerciseRecord.builder()
                .exerciseDuration(req.getExerciseDuration())
                .activityKcal(req.getActivityKcal())
                .effortLevel(req.getEffortLevel())
                .exercise(exerciseId)
                .startDatetime(req.getStartDatetime())
                .endDatetime(req.getEndDatetime())
                .build();


        return exerciseRecordRepository.save(exerciseRecord).getExerciseRecordId();
    }

    //    [GET] recordList -> page
    public List<ExerciseRecordGetRes> getExerciseLogList(PagingReq req) {
        PagingDto dto = PagingDto.builder()
                .size(req.getRowPerPage())
                .startIdx((req.getPage() - 1) * req.getRowPerPage())
                .build();

        return exerciseRecordMapper.findByLimitTo(dto);
    }

}
