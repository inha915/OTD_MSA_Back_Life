package com.otd.otd_msa_back_life.community.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    // application.yml: constants.file.directory: D:\inha\healthcare\img  (예시)
    @Value("${constants.file.directory}")
    private String rootDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path dir = Paths.get(rootDir).toAbsolutePath().normalize();
        // file:/ 스킴을 포함한 URI로 변환 (윈도우/리눅스 모두 호환)
        String location = dir.toUri().toString();

        registry.addResourceHandler("/static/community/**")
                .addResourceLocations(location) // 디스크 폴더 매핑
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic());
    }
}
