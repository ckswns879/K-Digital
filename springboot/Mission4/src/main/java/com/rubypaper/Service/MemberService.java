package com.rubypaper.Service;

import java.util.List;
import java.util.Map;

import com.rubypaper.domain.MemberDaoH2Impl;
import com.rubypaper.domain.MemberInterface;
import com.rubypaper.domain.MemberVO;
import com.rubypaper.domain.Log.LogDao;
import com.rubypaper.domain.Log.LogDaoFileImpl;

public class MemberService {

	private MemberInterface memberDao;

	private LogDao logDao;

	public MemberService() {
		memberDao = new MemberDaoH2Impl();
		//memberDao = new MemberDaoListImpl();
		
		//logDao = new LogDaoH2Impl();
		logDao = new LogDaoFileImpl();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		Map<String, Object> map = memberDao.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		if (list != null)	logDao.addLog("get", (String)map.get("sql"), true);
		else				logDao.addLog("get", (String)map.get("sql"), false);
		return list;
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		MemberVO member = (MemberVO) map.get("data");
		if (member != null)	logDao.addLog("get", (String)map.get("sql"), true);
		else				logDao.addLog("get", (String)map.get("sql"), false);
		return member;
	}

	public MemberVO addMember(MemberVO member) {
		Map<String, Object> map = memberDao.addMember(member);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("post", (String)map.get("sql"), true);
		else			logDao.addLog("post", (String)map.get("sql"), false);
		return m;		
	}

	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> map = memberDao.updateMember(member);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("put", (String)map.get("sql"), true);
		else			logDao.addLog("put", (String)map.get("sql"), false);	
		return m;
	}

	public MemberVO deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("delete", (String)map.get("sql"), true);
		else			logDao.addLog("delete", (String)map.get("sql"), false);
		return m;
	}
}
