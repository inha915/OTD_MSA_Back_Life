package com.otd.otd_msa_back_life.otd_community.repository;

import com.otd.otd_msa_back_life.otd_community.entity.CommunityPost;
import com.otd.otd_msa_back_life.otd_community.entity.CommunityPostFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityPostFileRepository extends JpaRepository<CommunityPostFile, Long> {
    List<CommunityPostFile> findByPost(CommunityPost post);
}
