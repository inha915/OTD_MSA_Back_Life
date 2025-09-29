package com.otd.otd_msa_back_life.community.config;

import com.otd.otd_msa_back_life.community.entity.CommunityCategory;
import com.otd.otd_msa_back_life.community.repository.CommunityCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"local","dev"}) // 필요 시: 로컬/개발에서만 시드
@RequiredArgsConstructor
public class CommunityBootstrap implements CommandLineRunner {

    private final CommunityCategoryRepository categoryRepo;

    @Override
    public void run(String... args) {
        seed("free", "자유수다");
        seed("diet", "다이어트");
        seed("work", "운동");
        seed("love", "연애");
    }

    private void seed(String key, String name) {
        categoryRepo.findByCategoryKey(key).ifPresentOrElse(
                exist -> {
                    // 이름이 바뀌었으면 업데이트
                    if (!name.equals(exist.getName())) {
                        exist.setName(name);
                        categoryRepo.save(exist);
                        log.info("Updated category: {} -> {}", key, name);
                    }
                },
                () -> {
                    // 생성자 없으면 빌더 사용 (권장)
                    CommunityCategory cat = CommunityCategory.builder()
                            .categoryKey(key)
                            .name(name)
                            .build();
                    categoryRepo.save(cat);
                    log.info("Seeded category: {} ({})", key, name);
                }
        );
    }
}
