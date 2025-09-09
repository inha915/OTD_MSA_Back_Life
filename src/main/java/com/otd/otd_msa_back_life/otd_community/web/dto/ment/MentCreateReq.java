package com.otd.otd_msa_back_life.otd_community.web.dto.ment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MentCreateReq {
    private Long memberNoLogin;
    private String content;
}
