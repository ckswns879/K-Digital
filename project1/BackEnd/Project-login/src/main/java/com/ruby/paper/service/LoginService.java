package com.ruby.paper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruby.paper.dao.LoginDao;
import com.ruby.paper.domain.MemberVO;

@Service
public class LoginService {

	private LoginDao dao;

	@Autowired
	public LoginService(LoginDao dao) {
		super();
		this.dao = dao;
	}	

	public MemberVO getMember(String id) {
		return dao.getMember(id);
	}

	public int addMember(MemberVO memberVO) {
		return dao.addMember(memberVO);
	}

	public int updateMember(String pw, String id) {
		return dao.updateMember(pw, id);
	}

	public int deleteMember(String id) {
		return dao.deleteMember(id);
	}

}
