package com.otd.otd_msa_back_life.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.otd.otd_msa_back_life.configuration.model.JwtUser;
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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserHeaderAuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String signedUserJson = request.getHeader("signedUser");
        log.info("signedUserJson: {}", signedUserJson);
        if (signedUserJson == null) {
            log.warn("signedUser header is missing!");
        }
        if (signedUserJson != null) {
            JwtUser jwtUser = objectMapper.readValue(signedUserJson, JwtUser.class);
            UserPrincipal userPrincipal = new UserPrincipal(jwtUser);

            Authentication auth =
                    new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
