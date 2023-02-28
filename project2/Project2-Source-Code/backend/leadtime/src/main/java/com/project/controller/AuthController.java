package com.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.MemberRequestDto;
import com.project.dto.MemberResponseDto;
import com.project.dto.TokenDto;
import com.project.dto.TokenRequestDto;
import com.project.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("/data")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/data/auth/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/data/auth/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/data/auth/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
    	System.out.println(tokenRequestDto.getRefreshToken());
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}