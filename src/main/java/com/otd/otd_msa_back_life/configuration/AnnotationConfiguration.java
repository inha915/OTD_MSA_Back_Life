package com.otd.otd_msa_back_life.configuration;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@ConfigurationPropertiesScan
@EnableDiscoveryClient

public class AnnotationConfiguration {}
