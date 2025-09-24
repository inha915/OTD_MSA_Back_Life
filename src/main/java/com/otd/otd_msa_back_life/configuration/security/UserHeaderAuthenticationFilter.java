package com.otd.otd_msa_back_life.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.otd.otd_msa_back_life.configuration.enumcode.model.EnumUserRole;
import com.otd.otd_msa_back_life.configuration.model.JwtUser;
import com.otd.otd_msa_back_life.configuration.model.JwtUserDto;
import com.otd.otd_msa_back_life.configuration.model.SignedUser;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.otd.otd_msa_back_life.configuration.model.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
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
        final String idHeader = request.getHeader("X-User-Id");
        final String rolesCsv  = request.getHeader("X-User-Roles"); // "ROLE_USER,ROLE_ADMIN" or null/empty

        if (idHeader == null || idHeader.isBlank()) {
            // 게이트웨이가 헤더를 안 붙인 요청은 익명으로 통과
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

        // 3) JwtUser 생성 후 UserPrincipal 생성 (UserPrincipal이 내부에서 ROLE_ 접두어로 SimpleGrantedAuthority를 만듦)
        JwtUser jwtUser = new JwtUser(userId, enumRoles);


        UserPrincipal principal = new UserPrincipal(jwtUser);

        // 4) Authentication 구성 (authorities는 principal.getAuthorities() 사용)
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        principal.getAuthorities() // Collection<? extends GrantedAuthority>
                );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 5) SecurityContext 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // (선택) 디버깅 로그
        if (log.isDebugEnabled()) {
            String granted = principal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
            log.debug("[LIFE] Auth set -> userId={}, authorities={}", userId, granted);
        }

        filterChain.doFilter(request, response);
    }
}