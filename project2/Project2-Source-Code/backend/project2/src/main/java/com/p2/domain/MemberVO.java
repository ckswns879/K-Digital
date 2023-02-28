package com.p2.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Table(name = "member")
@Entity
public class MemberVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pass;
	private String email;
	@Enumerated(EnumType.STRING)
	private Role authority;

	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	@Builder
	public MemberVO(Long id, String pass, String email, Role authority) {
		super();
		this.id = id;
		this.pass = pass;
		this.email = email;
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pass=" + pass + ", email=" + email + ", authority=" + authority + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getAuthority() {
		return authority;
	}

	public void setAuthority(Role authority) {
		this.authority = authority;
	}

}
