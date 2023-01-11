package com.rubypaper.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.Domain.MemberVO;

@RestController // 하위 메서드에 @ResponseBody 어노테이션을 붙이지 않아도 문자열과 JSON 등을 전송할 수 있다
public class MemberController {

	private List<MemberVO> MemberList; // 데이터를저장할곳 //생성자에서 데이터 한번만설정

	public MemberController() {
		MemberList = new ArrayList<MemberVO>();
		for (int i = 0; i <= 10; i++)
			MemberList.add(new MemberVO("id" + i, "pass" + i, i + "번쨰 이름", new Date())); // memberVO를만들고 MemberList에넣음
		System.out.println("Member");
	}

	@GetMapping("/member")
	public List<MemberVO> getMemberList() {
		System.out.println("getMemberList");
		return MemberList; // MemberList호출
	}

	@PostMapping("/member")
	public MemberVO insertMember(MemberVO m) {
		System.out.println("insertMember");
		MemberList.add(m); // MemberList에 새로운데이터m(MemberVO)를 넣음
		return m;
	}

	@GetMapping("/member/{id}") // 아이디검색
	public MemberVO getMember(@PathVariable String id) { // @PathVariable URL 경로에 변수를 넣어주는거
		System.out.println("getMember");
		for (MemberVO m : MemberList) {
			if (m.getId().equals(id))
				return m; // 아이디같으면 아이디와같은 membeVO를 보여줌
		}
		return null;
	}

	@PutMapping("/member")
	public MemberVO updateMembers(MemberVO memberVO) {
		System.out.println("updateMembers");
		for (MemberVO m : MemberList) {
			if (m.getId().equals(memberVO.getId())) {// 아이디가같으면 name, pass 변경
				m.setName(memberVO.getName());
				m.setPass(memberVO.getPass());
				return m;
			}
		}
		return null;
	}

	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable String id) {
		System.out.println("removeMember");
		for (MemberVO m : MemberList) {
			if (m.getId().equals(id)) { // 아이디가같으면 memberlist에서 삭제
				MemberList.remove(m);
				return m;
			}
		}
		return null;
	}
}