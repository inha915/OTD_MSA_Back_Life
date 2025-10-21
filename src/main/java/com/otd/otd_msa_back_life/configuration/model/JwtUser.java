package com.otd.otd_msa_back_life.configuration.model;

import com.otd.otd_msa_back_life.configuration.enumcode.model.EnumUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JwtUser {

    private long signedUserId;
    private String nickName;                      // ✅ 추가된 필드 (댓글 작성자 닉네임 등용)
    private List<EnumUserRole> roles;             // 인가 처리 시 사용

    /*
     role 이름은 ROLE_아무거나
     ROLE_USER, ROLE_ADMIN, ROLE_MANAGER, ROLE_LEVEL_1 ...
     */

    // ✅ 기존 코드 호환용 (UserHeaderAuthenticationFilter에서 사용)
    public JwtUser(long signedUserId, List<EnumUserRole> roles) {
        this.signedUserId = signedUserId;
        this.roles = roles;
    }
}
