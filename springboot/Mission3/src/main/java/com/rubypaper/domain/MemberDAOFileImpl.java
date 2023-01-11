package com.rubypaper.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class MemberDAOFileImpl implements MemberDAO {

	private List<MemberVO> MemberList;

	public MemberDAOFileImpl() {
		MemberList = new ArrayList<>();
		// list.txt에서 읽어오기
		try (BufferedReader br = new BufferedReader(new FileReader("list.txt"))) {
			String str;
			// readLine : 한줄씩 읽기
			while((str = br.readLine())!=null) {
				// 1줄 읽는동안 text 자르기
				StringTokenizer st = new StringTokenizer(str, ",");
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				String s3 = st.nextToken();
				// 읽은 내용 list에 memberVO로 넣어주기
				MemberList.add(new MemberVO(s1, s2, s3, new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
