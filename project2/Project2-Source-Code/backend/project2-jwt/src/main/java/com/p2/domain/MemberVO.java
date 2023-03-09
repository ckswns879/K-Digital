package com.p2.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

@Table(name = "member")
@Entity
public class MemberVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	private String pass;
	private String email;
	@Enumerated(EnumType.STRING)
	private Role authority;

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<PaymentLogVO> payment;

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<BasketVO> basket;

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
