package com.p2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.p2.dto.MemberResponseDto;
import com.p2.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	
    private final MemberRepository memberRepository;

    public MemberResponseDto findMemberInfoById(Long id) {
        return memberRepository.findById(id)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public MemberResponseDto findMemberInfoByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }
}
