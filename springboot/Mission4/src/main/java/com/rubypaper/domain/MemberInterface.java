package com.rubypaper.domain;

import java.util.Map;

import com.rubypaper.domain.MemberVO;

public interface MemberInterface {

	Map<String, Object> getMembers();

	Map<String, Object> getMember(Integer id);

	Map<String, Object> addMember(MemberVO member);

	Map<String, Object> updateMember(MemberVO member);

	Map<String, Object> deleteMember(Integer id);

}