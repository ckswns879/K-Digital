package com.ruby.paper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruby.paper.domain.MemberVO;
import com.ruby.paper.service.LoginService;

@RestController
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	public LoginController() {
		log.info("LoginController() 생성자 호출됨.");
	}
	

	@Autowired
	private LoginService loginService;

	@GetMapping("/read/{id}") // 로그인
	public MemberVO getUser(@PathVariable String id) {
		log.info(String.format("MemberController - readMember('%s')가 호출됨.", id));
		return loginService.getMember(id);
	}

	@PostMapping("/create") // 회원가입
	public int addUser(MemberVO userVO) {
		log.info("createmember 호출됨.");
		return loginService.addMember(userVO);
	}

	@PutMapping("/update/{id}") // 비밀번호변경
	public int updateUser(@PathVariable String id, String pass) {
		log.info(String.format("MemberController - updateMember('%s','%s')가 호출됨.", id, pass));
		return loginService.updateMember(id, pass);
	}

	@DeleteMapping("/delete/{id}") // 아이디 삭제
	public int deleteUser(@PathVariable String id) {
		log.info(String.format("MemberController - deleteMember('%s')가 호출됨.", id));
		return loginService.deleteMember(id);
	}

}