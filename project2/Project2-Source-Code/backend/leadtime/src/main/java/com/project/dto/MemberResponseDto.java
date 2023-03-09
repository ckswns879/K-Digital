package com.project.dto;

import java.util.List;

import com.project.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
	//회원가입, 로그인을 위한 dto
	
    private String email;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail());
    }
}