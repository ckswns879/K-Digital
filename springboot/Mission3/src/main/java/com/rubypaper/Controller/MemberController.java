package com.rubypaper.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.Service.MemberService;
import com.rubypaper.domain.MemberVO;

@RestController // 하위 메서드에 @ResponseBody 어노테이션을 붙이지 않아도 문자열과 JSON 등을 전송할 수 있다
public class MemberController {

	private MemberService memberService;
	public MemberController() {
		memberService = new MemberService();
		System.out.println("===> MemberController interface 샘플");
	}

	@GetMapping("/member")
	public List<MemberVO> getMemberList() {
		return memberService.getMembers();

	}

	@PostMapping("/member")
	public MemberVO insertMember(MemberVO m) {
		return memberService.insertMember(m);

	}

	@GetMapping("/member/{id}") // 아이디검색
	public MemberVO getMember(@PathVariable String id) {
		return memberService.getMember(id);//?

	}

	@PutMapping("/member")
	public MemberVO updateMembers(MemberVO memberVO) {
		return memberService.updateMembers(memberVO);

	}

	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable String id) {
		return memberService.removeMembers(id);

	}

}