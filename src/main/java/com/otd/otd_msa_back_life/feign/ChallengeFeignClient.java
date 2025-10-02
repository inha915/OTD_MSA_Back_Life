package com.otd.otd_msa_back_life.feign;

import com.otd.otd_msa_back_life.feign.model.ChallengeProgressUpdateReq;
import com.otd.otd_msa_back_life.feign.model.ChallengeRecordDeleteReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "challenge",
        url = "${constants.feign-client.challenge.url}")
public interface ChallengeFeignClient {

    @PostMapping("/progress/update")
    void updateProgressByExercise(@RequestBody ChallengeProgressUpdateReq req);

    @DeleteMapping("/record/delete")
    void deleteRecordByExercise(@RequestBody ChallengeRecordDeleteReq req);
}
