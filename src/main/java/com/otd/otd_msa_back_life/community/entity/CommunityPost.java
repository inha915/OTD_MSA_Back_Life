package com.otd.otd_msa_back_life.community.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;


    private String title;

    @Column(nullable = false)



}
