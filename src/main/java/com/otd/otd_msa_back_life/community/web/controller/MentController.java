package com.otd.otd_msa_back_life.community.web.controller;

import com.otd.otd_msa_back_life.community.service.MentService;
import com.otd.otd_msa_back_life.community.web.dto.ment.MentCreateReq;
import com.otd.otd_msa_back_life.community.web.dto.ment.MentRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class MentController {

    private final MentService mentService;

    @GetMapping("/posts/{postId}/ments")
    public List<MentRes> list(@PathVariable Long postId) {
        return mentService.listByPost(postId);
    }

    @PostMapping("/posts/{postId}/ments")
    public MentRes create(@PathVariable Long postId,
                          @RequestBody MentCreateReq req) {
        return mentService.create(postId, req);
    }

    @DeleteMapping("/ments/{mentId}")
    public void delete(@PathVariable Long mentId,
                       @RequestHeader("X-MEMBER-ID") Long requesterId) {
        mentService.delete(mentId, requesterId);
    }
}
