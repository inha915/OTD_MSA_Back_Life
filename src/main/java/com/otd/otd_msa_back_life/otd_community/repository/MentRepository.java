package com.otd.otd_msa_back_life.otd_community.repository;

import com.otd.otd_msa_back_life.otd_community.entity.Ment;
import com.otd.otd_msa_back_life.otd_community.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentRepository extends JpaRepository<Ment, Long> {
    List<Ment> findByPostOrderByCreatedAtAsc(CommunityPost post);
}
