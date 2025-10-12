package com.otd.otd_msa_back_life.admin.service;

import com.otd.otd_msa_back_life.admin.mapper.AdminMapper;
import com.otd.otd_msa_back_life.admin.model.*;
import com.otd.otd_msa_back_life.admin.model.AdminCommunityDataDto;
import com.otd.otd_msa_back_life.admin.model.AdminCommunityGetRes;
import com.otd.otd_msa_back_life.admin.model.AdminExerciseDto;
import com.otd.otd_msa_back_life.admin.model.AdminMealDataDto;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import com.otd.otd_msa_back_life.community.entity.CommunityPostFile;
import com.otd.otd_msa_back_life.community.entity.Ment;
import com.otd.otd_msa_back_life.community.repository.CommunityLikeRepository;
import com.otd.otd_msa_back_life.community.repository.CommunityPostFileRepository;
import com.otd.otd_msa_back_life.community.repository.CommunityPostRepository;
import com.otd.otd_msa_back_life.community.repository.MentRepository;
import com.otd.otd_msa_back_life.configuration.model.ResultResponse;
import com.otd.otd_msa_back_life.exercise.repository.ExerciseRecordRepository;
import com.otd.otd_msa_back_life.meal.entity.MealRecordDetail;
import com.otd.otd_msa_back_life.meal.repository.MealRecordDetailRepository;
import com.otd.otd_msa_back_life.water_intake.repository.DailyWaterIntakeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    private final MealRecordDetailRepository mealRecordDetailRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final DailyWaterIntakeRepository dailyWaterIntakeRepository;

    private final MentRepository mentRepository;
    private final CommunityPostRepository communityPostRepository;
    private final CommunityLikeRepository communityLikeRepository;
    private final CommunityPostFileRepository communityPostFileRepository;

    public List<AdminCommunityGetRes> getCommunity() {
        return adminMapper.findAllCommunity();
    }

    public List<AdminExerciseDto> getExerciseData(Long userId){
        return adminMapper.findExerciseDataByUserId(userId);
    }

    public List<MealRecordDetail> getMealData(Long userId) {
        return mealRecordDetailRepository.findAllByUserId(userId);
    }

    public List<AdminMealDetailGetRes> getMealDetail(AdminMealDetailGetReq req) {
        return adminMapper.findMealDetailByUserIdAndMealDayAndMealTime(req);
    }

    @Transactional
    public void removeUser(Long userId) {
        try {
            // 운동 기록 삭제
            exerciseRecordRepository.deleteAllByUserId(userId);

            // 식단 기록 삭제
            mealRecordDetailRepository.deleteAllByUserId(userId);

            // 물 섭취 기록 삭제
            dailyWaterIntakeRepository.deleteAllByUserId(userId);

            // 댓글 삭제
            mentRepository.deleteAllByUserId(userId);
            // 좋아요 삭제
            communityLikeRepository.deleteAllByUserId(userId);
            // 게시글 파일 삭제
            communityPostFileRepository.deleteAllByUserId(userId);
            // 게시글 삭제(소프트 딜리트)
            int result = communityPostRepository.softDeleteByUserId(userId);
            log.info("Life 서버: 유저 {} 관련 데이터 삭제 완료", userId);
            log.info("Life 게시글 삭제 수 : {}", result);
        } catch (Exception e) {
            log.error("Life 서버: 유저 {} 데이터 삭제 중 오류 발생", userId, e);
            throw e; // controller에서 catch해서 실패 Response 내려줌
        }
    }

    public AdminCommunityDataDto getCommunityData(Long postId) {
        CommunityPost post = communityPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 삭제 됨"));

        List<AdminCommunityDataDto.CommentDto> comments =
                mentRepository.findByPostPostId(postId).stream()
                        .map(c -> new AdminCommunityDataDto.CommentDto(c.getCommentId(), c.getContent()))
                        .toList();

        List<AdminCommunityDataDto.FileDto> files =
                communityPostFileRepository.findByPostPostId(postId).stream()
                        .map(f -> new AdminCommunityDataDto.FileDto(f.getId(), f.getFilePath()))
                        .toList();

        return AdminCommunityDataDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .isDeleted(post.getIsDeleted())
                .comments(comments)
                .files(files)
                .build();
    }

    @Transactional
    public ResultResponse<?> removeCommunity(Long postId) {
        int postResult = communityPostRepository.softDeleteByPostId(postId);
        int likeCount = communityLikeRepository.deleteByPostId(postId);
        int fileCount = communityPostFileRepository.deleteByPostId(postId);
        int commentCount = mentRepository.deleteByPostId(postId);

        int sum = postResult + likeCount + fileCount + commentCount;
        log.info("Deleted postId={}, result: post={}, comment={}, like={}, file={}",
                postId, postResult, commentCount, likeCount, fileCount);

        return new ResultResponse<>(String.format("게시글 삭제 완료 (댓글 %d, 좋아요 %d, 파일 %d)"
                , commentCount, likeCount, fileCount), sum);
    }
}
