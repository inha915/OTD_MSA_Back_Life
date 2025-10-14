package com.otd.otd_msa_back_life.body_composition.repository;

import com.otd.otd_msa_back_life.body_composition.entity.BasicBodyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasicBodyInfoRepository extends JpaRepository<BasicBodyInfo, Long> {
    List<BasicBodyInfo> findByUserId(Long userId);
}
