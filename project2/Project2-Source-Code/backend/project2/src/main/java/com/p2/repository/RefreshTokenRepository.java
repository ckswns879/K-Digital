package com.p2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2.domain.RefreshTokenVO;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenVO, String> {
    Optional<RefreshTokenVO> findByKey(String key);  //Member ID 값으로 토큰을 가져오기 위해 findByKey 만 추가했습니다.
}