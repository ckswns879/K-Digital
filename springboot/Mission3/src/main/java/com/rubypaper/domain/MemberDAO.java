package com.rubypaper.domain;

import java.util.List;

public interface MemberDAO {

	List<MemberVO> getMemberList();

	MemberVO insertMember(MemberVO m);

	MemberVO getMember(String id);

	MemberVO updateMembers(MemberVO memberVO);

	MemberVO removeMember(String id);

}