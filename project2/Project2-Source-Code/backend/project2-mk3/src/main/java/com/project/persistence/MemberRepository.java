package com.project.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

}
