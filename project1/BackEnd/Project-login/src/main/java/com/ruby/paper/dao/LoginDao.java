package com.ruby.paper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ruby.paper.domain.MemberVO;

@Repository
public class LoginDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public LoginDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public MemberVO getMember(String id) {
		String sqlString = String.format("select id, pw from Member where id = '%s'", id);
		MemberVO member = jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		return member;
	}

	public int addMember(MemberVO memberVO) {
		String sql = String.format("insert into Member (id, pw) value ('%s', '%s')", memberVO.getId(), memberVO.getPass());
		return jdbcTemplate.update(sql);
	}

	public int updateMember(String id, String pw) {
		String sqlString = String.format("update Member set pw = '%s' where id = '%s'", pw, id);
		int member = jdbcTemplate.update(sqlString);
		return member;
	}

	public int deleteMember(String id) {
		String sqlString = String.format("delete from Member where id = '%s'", id);
		int member = jdbcTemplate.update(sqlString);
		return member;
	}

}