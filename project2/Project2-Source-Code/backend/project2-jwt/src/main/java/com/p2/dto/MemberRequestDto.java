package com.p2.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.p2.domain.MemberVO;
import com.p2.domain.Role;

public class MemberRequestDto {

	private String email;
	private String pass;

	public MemberRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public MemberRequestDto(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "MemberRequestDto [email=" + email + ", pass=" + pass + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
    public MemberVO toMember(PasswordEncoder passwordEncoder) {
        return MemberVO.builder()
                .email(email)
                .pass(passwordEncoder.encode(pass))
                .authority(Role.ROLE_MEMBER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, pass);
    }

}
