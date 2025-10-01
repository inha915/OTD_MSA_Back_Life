package com.otd.otd_msa_back_life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@ConfigurationPropertiesScan
@EnableFeignClients(basePackages = {"com.otd.otd_msa_back_life.community.client"
                                  , "com.otd.otd_msa_back_life.challenge"})
@SpringBootApplication
public class OtdMsaBackLifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtdMsaBackLifeApplication.class, args);
    }

}
