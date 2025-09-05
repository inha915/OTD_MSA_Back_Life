package com.otd.otd_msa_back_life.application.otd_community.web.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateReq {
    private Long memberNoLogin;
    private String title;
    private String content;
}
