package com.otd.otd_msa_back_life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class OtdMsaBackLifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtdMsaBackLifeApplication.class, args);
    }

}
