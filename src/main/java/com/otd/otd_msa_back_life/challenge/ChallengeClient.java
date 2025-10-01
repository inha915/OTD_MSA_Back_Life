package com.otd.otd_msa_back_life.challenge;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "challenge",
             url = "${constants.feign-client.challenge.url}")
public interface ChallengeClient {
    @PatchMapping("/progress/update")
    void updateProgressByExercise(@RequestBody ChallengeProgressUpdateReq req);
}
