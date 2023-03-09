package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.jwt.JwtAccessDeniedHandler;
import com.project.jwt.JwtAuthenticationEntryPoint;
import com.project.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // CSRF 설정 Disable
        http.csrf().disable()

            // exception handling 할 때 우리가 만든 클래스를 추가
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // 시큐리티는 기본적으로 세션을 사용
            // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


            .and()
            .authorizeRequests()
            
            //cors 에러중 preflight에러는 특별한 목적을 가지는 요청으로 method=OPTIONS를 가짐
            //위와 같이 추가해서 에러 해결...(https://ahndding.tistory.com/17)
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            
            //로그인 회원가입등은 권한 없이 수행되서 permitAll
            .antMatchers("/data/auth/**").permitAll()
            
            //서비스 시작하면 기본으로 받아오는 검색 전용 리스트도 permitAll로 변경해둠
            .antMatchers("/data/get").permitAll()
            
            .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요

            // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
            .and()
            .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}