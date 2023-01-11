package com.rubypaper.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.rubypaper.domain.MemberDAO;
import com.rubypaper.domain.MemberVO;

public class MemberService {
	private List<MemberVO> MemberList;
	private MemberDAO memberDAO;

	public MemberService() {
		System.out.println("===> MemberService dao 샘플");

		memberDAO = new MemberDAO();

		MemberList = new ArrayList<MemberVO>();
		for (int i = 1; i <= 5; i++)
			MemberList.add(new MemberVO("h2id" + i, i + "번째 이름", "h2pass" + i, new Date()));
		System.out.println("===> MemberController dao 샘플");
	}

	// 출력 - 모든 멤버 정보
	public List<MemberVO> getMembers() {
		System.out.println("getMembersList");
		return memberDAO.getMemberList();
	}


	// 추가 - member를 추가한다
	public MemberVO insertMember(MemberVO memberVO) {
		System.out.println("insertMember");
		return memberDAO.insertMember(memberVO);
	}
	// 출력 - 아이디가 {id}인 멤버를 출력
	public MemberVO getMember(String id) {
		System.out.println("id 검색");
		return memberDAO.getMember(id);//?
	}
	// 수정
	public MemberVO updateMembers(MemberVO memberVO) {
		System.out.println("updateMembers");
		return memberDAO.updateMembers(memberVO);
	}
	// 삭제
	public MemberVO removeMembers(String id) {
		System.out.println("removeMembers");
		return memberDAO.removeMember(id);
	}
}