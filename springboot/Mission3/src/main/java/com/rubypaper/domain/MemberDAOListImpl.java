package com.rubypaper.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDAOListImpl implements MemberDAO {

	private List<MemberVO> MemberList;

	public MemberDAOListImpl() {
		MemberList = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			MemberList.add(new MemberVO("inter id" + i, i + "번째 이름", "inter pass" + i, new Date()));
		}
	}

	@Override
	public List<MemberVO> getMemberList() {
		return MemberList;
	}

	@Override
	public MemberVO insertMember(MemberVO m) {
		MemberList.add(m);
		return m;
	}

	@Override
	public MemberVO getMember(String id) {
		for (MemberVO m : MemberList) {
			if (m.getId().equals(id))
				return m;
		}
		return null;
	}
	@Override
	public MemberVO updateMembers(MemberVO memberVO) {
		for (MemberVO m : MemberList) {
			if (m.getId().equals(memberVO.getId())) {
				m.setName(memberVO.getName());
				m.setPass(memberVO.getPass());
				return m;
			}
		}
		return null;
	}
	@Override
	public MemberVO removeMember(String id) {
		for (MemberVO m : MemberList) {
			if (m.getId().equals(id)) {
				MemberList.remove(m);
				return m;
			}
		}
		return null;
	}
}
