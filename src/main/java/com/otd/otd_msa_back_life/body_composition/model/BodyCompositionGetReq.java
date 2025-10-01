package com.otd.otd_msa_back_life.body_composition.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BodyCompositionGetReq {
    private LocalDateTime start;
    private LocalDateTime end;

    public BodyCompositionGetReq(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
}
