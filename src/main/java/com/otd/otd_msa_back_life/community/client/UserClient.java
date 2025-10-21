package com.otd.otd_msa_back_life.community.client;

import com.otd.otd_msa_back_life.community.client.dto.UserRes;
import com.otd.otd_msa_back_life.community.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${constants.feign-client.challenge.name}",
        url  = "${constants.feign-client.challenge.url}",
        configuration = FeignConfig.class
)
public interface UserClient {
    @GetMapping("/api/v1/users/{userId}")
    UserRes getUserById(@PathVariable("userId") Long userId);
}
