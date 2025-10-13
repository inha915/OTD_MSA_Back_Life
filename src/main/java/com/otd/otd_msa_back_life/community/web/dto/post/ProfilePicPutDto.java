package com.otd.otd_msa_back_life.community.web.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilePicPutDto {
    private Long userId;
    private String nickname;
    private String imgPath;
}
