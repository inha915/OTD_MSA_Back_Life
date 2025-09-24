package com.otd.otd_msa_back_life.community.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRes {
    private Long memberId;
    private String nickname;
    private String email;
    private String profileImg;
}
