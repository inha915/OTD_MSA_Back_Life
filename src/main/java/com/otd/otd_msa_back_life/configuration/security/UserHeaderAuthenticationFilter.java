package com.otd.otd_msa_back_life.configuration.security;

import com.otd.otd_msa_back_life.configuration.enumcode.model.EnumUserRole;
import com.otd.otd_msa_back_life.configuration.model.JwtUser;
import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserHeaderAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1) 헤더에서 값 읽기
        final String idHeader       = opt(request.getHeader("X-User-Id"),
                request.getHeader("X-MEMBER-ID")); // 구버전 호환
        final String rolesCsv       = request.getHeader("X-User-Roles");      // e.g. "ROLE_USER,ROLE_ADMIN"
        final String nickNameHeader = request.getHeader("X-User-Nickname");   // 닉네임(있으면 사용)

        if (idHeader == null || idHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        final Long userId;
        try {
            userId = Long.parseLong(idHeader);
        } catch (NumberFormatException e) {
            log.warn("[LIFE] X-User-Id 형식 오류: {}", idHeader);
            filterChain.doFilter(request, response);
            return;
        }

        // 2) rolesCsv -> EnumUserRole 리스트로 변환 (ROLE_ 접두어 제거 후 enum 매칭)
        final List<EnumUserRole> enumRoles =
                (rolesCsv == null || rolesCsv.isBlank())
                        ? Collections.emptyList()
                        : Arrays.stream(rolesCsv.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(role -> role.startsWith("ROLE_") ? role.substring(5) : role)
                        .map(roleName -> {
                            try {
                                return EnumUserRole.valueOf(roleName);
                            } catch (IllegalArgumentException ex) {
                                log.warn("[LIFE] 알 수 없는 역할 값 무시: {}", roleName);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        // 3) JwtUser 생성 및 닉네임 세팅
        JwtUser jwtUser = new JwtUser(userId, enumRoles);
        if (nickNameHeader != null && !nickNameHeader.isBlank()) {
            jwtUser.setNickName(nickNameHeader);
        }

        // 4) Principal/Authentication 구성
        UserPrincipal principal = new UserPrincipal(jwtUser);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        principal.getAuthorities()
                );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 5) SecurityContext 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (log.isDebugEnabled()) {
            String granted = principal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            log.debug("[LIFE] Auth set -> userId={}, nickName='{}', authorities={}",
                    userId, jwtUser.getNickName(), granted);
        }

        filterChain.doFilter(request, response);
    }

    private static String opt(String a, String b) {
        return (a != null && !a.isBlank()) ? a : b;
    }
}
