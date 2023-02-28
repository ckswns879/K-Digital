package com.ruby.paper.domain;

public class MemberVO {

	private String id;
	private String pass;

	public MemberVO() {
		super();
	}

	public MemberVO(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pass=" + pass + "]";
	}

}
