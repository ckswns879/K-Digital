package com.p2.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p2.domain.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, Long > {
    Optional<MemberVO> findByEmail(String email);
    boolean existsByEmail(String email);		//중복가입방지
}