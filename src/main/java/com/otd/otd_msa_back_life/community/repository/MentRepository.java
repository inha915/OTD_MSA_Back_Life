package com.otd.otd_msa_back_life.community.repository;

import com.otd.otd_msa_back_life.community.entity.Ment;
import com.otd.otd_msa_back_life.community.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentRepository extends JpaRepository<Ment, Long> {
    List<Ment> findByPostOrderByCreatedAtAsc(CommunityPost post);
}
