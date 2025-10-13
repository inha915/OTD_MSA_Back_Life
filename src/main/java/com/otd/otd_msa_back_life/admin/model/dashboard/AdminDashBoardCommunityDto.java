package com.otd.otd_msa_back_life.admin.model.dashboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminDashBoardCommunityDto {
    private int totalPostCount;
    private int weeklyNewPostCount;
    private List<CategoryPostCountRes> categoryPostCount;
    private List<TopLikePostRes> topLikePost;
    private List<TopCommentPostRes> topCommentPost;
}
