package com.otd.otd_msa_back_life.application.otd_community.web.controller;

import com.otd.otd_msa_back_life.application.otd_community.client.dto.UserRes;
import com.otd.otd_msa_back_life.application.otd_community.service.UserServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/community/test")
@RequiredArgsConstructor
public class TestController {

    private final UserServiceProxy userServiceProxy;

    @GetMapping("/user/{id}")
    public UserRes testGetUser(@PathVariable("id") Long id) {
        return userServiceProxy.getUserInfo(id);
    }
}
