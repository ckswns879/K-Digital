package com.rubypaper.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.Service.MemberService;
import com.rubypaper.domain.MemberVO;



@RestController // 하위 메서드에 @ResponseBody 어노테이션을 붙이지 않아도 문자열과 JSON 등을 전송할 수 있다
public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		System.out.println("LOG MemberController() 생성자가 호출됨.");
		memberService = new MemberService();
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		System.out.println("MemberController - getMembers()가 호출됨.");
		return memberService.getMembers();
	}

	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		System.out.println(String.format("MemberController - getMember(%d)가 호출됨.", id));
		return memberService.getMember(id);
	}

	@GetMapping("/member/body") // JSON으로 데이터를 요청하는 경우
	public MemberVO getMemberbyJSON(@RequestBody MemberVO member) {
		System.out.println(String.format("MemberController - getMemberbyJSON(%s)이 호출됨.", member));
		return memberService.getMember(member.getId());
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO member) {
		System.out.println(String.format("MemberController - addMember(%s)가 호출됨.", member));
		return memberService.addMember(member);
	}

	@PutMapping("/member")
	public MemberVO updateMember(MemberVO member) {
		System.out.println(String.format("MemberController - updateMember(%s)가 호출됨.", member));
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO deleteMember(@PathVariable Integer id) {
		System.out.println(String.format("MemberController - deleteMember(%d)가 호출됨.", id));
		return memberService.deleteMember(id);
	}
}
