package com.p2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.domain.MemberVO;
import com.p2.repository.MemberRepository;

@Service
public class LoginService {

	@Autowired
	private MemberRepository memberRepository;
	
	public MemberVO getMember(Long id) {
		MemberVO m = memberRepository.findById(id).get(); 

		return m;
	}
	
	public MemberVO addMember(MemberVO member) {
		member = memberRepository.save(member); 
		return member;
	}
	
	public MemberVO updateMember(MemberVO member) {
		MemberVO m = memberRepository.findById(member.getId()).get();
		m.setPass(member.getPass());
		return memberRepository.save(m);
	}
	
	public MemberVO deleteMember(Long id) {
		MemberVO m = memberRepository.findById(id).get(); 
		memberRepository.deleteById(id);
		return m;
	}


}
