package com.otd.otd_msa_back_life.community.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class StaticResourceConfig implements WebMvcConfigurer {

    private final FileProperties props;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String root = props.getUploadDirectory();
        if (!StringUtils.hasText(root)) {
            throw new IllegalStateException("'file.upload-directory' 가 비어있습니다.");
        }

        // /static/community/** -> [uploadDir]/community/
        Path communityDir = Paths.get(root, "community").toAbsolutePath().normalize();
        registry.addResourceHandler("/static/community/**")
                .addResourceLocations(toFileLocation(communityDir));

        // /static/profile/** -> [uploadDir]/[profilePic]/
        String profileFolder = StringUtils.hasText(props.getProfilePic()) ? props.getProfilePic() : "profile";
        Path profileDir = Paths.get(root, profileFolder).toAbsolutePath().normalize();
        registry.addResourceHandler("/static/profile/**")
                .addResourceLocations(toFileLocation(profileDir));
    }

    private String toFileLocation(Path dir) {
        // 반드시 "file:/abs/path/" 형태로 끝에 슬래시 포함
        String uri = dir.toUri().toString();
        return uri.endsWith("/") ? uri : uri + "/";
    }
}
