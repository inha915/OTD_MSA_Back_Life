package com.otd.otd_msa_back_life.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile("default")
@FeignClient(name = "${constants.feign-client.challenge.name}" ,url = "${constants.feign-client.challenge.url}"   )
public interface ChallengeFeignClientLocal extends ChallengeFeignClient {
}
