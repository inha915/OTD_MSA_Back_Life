package com.otd.otd_msa_back_life.challenge;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChallengeProgressUpdateReq {
    private Long userId;
    private String name;
    private double record;
}
