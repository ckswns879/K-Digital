package com.p2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	
	@GetMapping("/")
	public String index() {
		System.out.println("index요청");
		return "index";
	}
	
	@GetMapping("/member")
	public String member() {
		System.out.println("member요청");
		return "member";
	}
	
	@GetMapping("/manager")
	public String manager() {
		System.out.println("manager요청");
		return "manager";
	}
	
	@GetMapping("/admin")
	public String admin() {
		System.out.println("admin요청");
		return "admin";
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
}
