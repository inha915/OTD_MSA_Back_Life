package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
}
