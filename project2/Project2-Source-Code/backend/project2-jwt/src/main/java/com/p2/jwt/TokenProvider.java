package com.p2.jwt;


import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.p2.dto.TokenDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenProvider {//JWT 토큰에 관련된 암호화, 복호화, 검증 로직은 다 이곳에서 이루어집니다.

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(Authentication authentication) {  //유저 정보를 넘겨받아서 Access Token 과 Refresh Token 을 생성합니다.
        // 권한들 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())       // payload "sub": "name"
                .claim(AUTHORITIES_KEY, authorities)        // payload "auth": "ROLE_MEMBER"
                .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
                .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))//만료시간설정
                .signWith(key, SignatureAlgorithm.HS512)// signWith() 메서드를 사용하여 JWT에 서명 이때, key 변수는 서명에 사용할 비밀키를, SignatureAlgorithm.HS512는 서명 알고리즘을 지정합니다.
                										// HS512는 HMAC-SHA512 서명 알고리즘을 의미합니다.
                .compact();			//compact() 메서드를 사용하여 JWT를 문자열 형태로 변환합니다. 이 문자열이 access token이 됩니다.					
        return TokenDto.builder() //TokenDto 객체를 생성하여 생성된 JWT와 함께 반환합니다. 
                .grantType(BEARER_TYPE) //BEARER_TYPE 상수를 grantType 속성으로 지정
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime()) 
                .refreshToken(refreshToken) // 생성된 access token, 만료 시간, 그리고 refreshToken을 포함합니다
                .build();		//최종적으로, return 키워드를 사용하여 TokenDto 객체를 반환합니다.
    }

    public Authentication getAuthentication(String accessToken) { // Access Token 에만 유저 정보를 담기 때문에 명시적으로 accessToken 을 파라미터로 받게 했습니다.
        // 토큰 복호화												  // Refresh Token 에는 아무런 정보 없이 만료일자만 담았습니다.
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) { //토큰 정보를 검증
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); //parseClaims 메소드는 만료된 토큰이어도 정보를 꺼내기 위해서 따로 분리
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}