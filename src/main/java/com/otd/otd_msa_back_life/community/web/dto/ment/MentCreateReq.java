package com.otd.otd_msa_back_life.community.web.dto.ment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MentCreateReq {
    private Long userId;
    private String content;
}
