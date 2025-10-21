package com.otd.otd_msa_back_life.community.web.dto.ment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MentCreateReq {
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    private String nickName;
}
