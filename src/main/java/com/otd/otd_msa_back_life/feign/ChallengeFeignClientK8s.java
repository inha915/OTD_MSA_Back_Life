package com.otd.otd_msa_back_life.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@FeignClient(name = "${constants.feign-client.challenge.name}"    )
public interface ChallengeFeignClientK8s extends ChallengeFeignClient {
}
