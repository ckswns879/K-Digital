package com.rubypaper.Service;

//import java.util.ArrayList;
//import java.util.Date;
import java.util.List;



import com.rubypaper.domain.MemberDAO;
import com.rubypaper.domain.MemberDAOH2Impl;
import com.rubypaper.domain.MemberVO;





public class MemberService {
//	private List<MemberVO> MemberList;
	private MemberDAO memberDAO;

	public MemberService() {
		

		System.out.println("===> MemberService interface 샘플");

		memberDAO = new MemberDAOH2Impl();
		//memberDAO = new MemberDAOListImpl();
	
	}

	// 출력 - 모든 멤버 정보
	public List<MemberVO> getMembers() {
		System.out.println("getMembersList(interface)");
		return memberDAO.getMemberList();
	}


	// 추가 - member를 추가한다
	public MemberVO insertMember(MemberVO memberVO) {
		System.out.println("insertMember(interface)");
		return memberDAO.insertMember(memberVO);
	}
	// 출력 - 아이디가 {id}인 멤버를 출력
	public MemberVO getMember(String id) {
		System.out.println("id 검색(interface)");
		return memberDAO.getMember(id);//?
	}
	// 수정
	public MemberVO updateMembers(MemberVO memberVO) {
		System.out.println("updateMembers(interface)");
		return memberDAO.updateMembers(memberVO);
	}
	// 삭제
	public MemberVO removeMembers(String id) {
		System.out.println("removeMembers(interface)");
		return memberDAO.removeMember(id);
	}
}