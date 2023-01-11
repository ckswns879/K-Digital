package com.rubypaper.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends JDBConnectH2 {

	public MemberDAO() {
		super();
	}

	public List<MemberVO> getMemberList() {

		List<MemberVO> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from member");
			while (rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getString("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public MemberVO insertMember(MemberVO m) {
		String query = "INSERT INTO member(id, pass, name) VALUES (?, ?, ?)"; // 쿼리문 템플릿
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, m.getId());
			ps.setString(2, m.getPass());
			ps.setString(3, m.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}

	public MemberVO getMember(String id) {
		MemberVO m = new MemberVO(); // 회원 정보 m 객체 생성
		String query = "SELECT * FROM member WHERE id=?"; // 쿼리문 템플릿
		try {

			// 쿼리 실행
			PreparedStatement ps = con.prepareStatement(query); // 동적 쿼리문 준비
			ps.setString(1, id); // 쿼리문의 첫 번째 인파라미터에 값 설정
			rs = ps.executeQuery(); // 쿼리문 실행

			// 결과 처리
			while (rs.next()) {
				if (rs.getString("id").equals(id)) {
					// 쿼리 결과로 얻은 회원 정보를 m 객체에 저장
					m.setId(rs.getString("id"));
					m.setPass(rs.getString(2));
					m.setName(rs.getString(3));
					m.setRegidate(rs.getDate(4));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}

	public MemberVO updateMembers(MemberVO memberVO) {
		String query = " update member set pass = ? , name = ? where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, memberVO.getPass());
			ps.setString(2, memberVO.getName());
			ps.setString(3, memberVO.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberVO;
	}

	public MemberVO removeMember(String id) {
		MemberVO mv = getMember(id);
		String query = "delete from member where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}
}