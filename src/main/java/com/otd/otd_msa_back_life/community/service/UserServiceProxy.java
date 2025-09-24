package com.otd.otd_msa_back_life.community.service;

import com.otd.otd_msa_back_life.community.client.UserClient;
import com.otd.otd_msa_back_life.community.client.dto.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceProxy {

    private final UserClient userClient;

    public UserRes getUserInfo(Long memberId) {
        return userClient.getUserById(memberId);
    }
}
