package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.CommunityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityCategoryRepository extends JpaRepository<CommunityCategory, Long> {
    Optional<CommunityCategory> findByCategoryKey(String categoryKey);
    boolean existsByCategoryKey(String categoryKey);
}
