package com.p2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p2.dto.MemberRequestDto;
import com.p2.dto.MemberResponseDto;
import com.p2.dto.TokenDto;
import com.p2.dto.TokenRequestDto;
import com.p2.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth") //SecurityConfig 에서 /auth/** 요청은 전부 허용했기 때문에 토큰 검증 로직을 타지 않습니다.
@RequiredArgsConstructor
public class AuthController {  //회원가입 / 로그인 / 재발급 을 처리하는 API 입니다.
    private final AuthService authService;

    @PostMapping("/signup") 
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) { 
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}