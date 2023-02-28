package com.p2.dto;

import com.p2.domain.MemberVO;

public class MemberResponseDto {  //MemberRequestDto 에는 사용자가 로그인 시도한 ID / PW String 이 존재합니다.
	private String email;

	public MemberResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberResponseDto [email=" + email + "]";
	}

	public MemberResponseDto(String email) {
		super();
		this.email = email;
	}

	public static MemberResponseDto of(MemberVO member) {
		return new MemberResponseDto(member.getEmail());
	}
}