package com.otd.otd_msa_back_life.otd_community.client;

import com.otd.otd_msa_back_life.otd_community.client.dto.UserRes;
import com.otd.otd_msa_back_life.otd_community.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "userClient",
        url = "http://112.222.157.157:5012/first_project", // 임시 User 서버 주소
        configuration = FeignConfig.class
)
public interface UserClient {

    @GetMapping("/api/v1/users/{memberId}")
    UserRes getUserById(@PathVariable("memberId") Long memberId);
}
