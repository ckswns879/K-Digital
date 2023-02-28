package com.p2.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.p2.domain.MemberVO;
import com.p2.domain.Role;
import com.p2.repository.MemberRepository;


@SpringBootTest
@Commit
public class PasswordEncoderTest {    				//암호화테스트
	
	@Autowired
	private MemberRepository memberrepo;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		MemberVO member = new MemberVO();
		member.setId(null);
		member.setEmail("testmail@test.test");
		member.setPass(encoder.encode("test1"));
		member.setAuthority(Role.ROLE_MANAGER);
		memberrepo.save(member);

	}	

}
