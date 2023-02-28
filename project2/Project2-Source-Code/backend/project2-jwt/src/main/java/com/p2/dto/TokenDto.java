package com.p2.dto;

import lombok.Builder;


public class TokenDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    
    public TokenDto() {
		// TODO Auto-generated constructor stub
	}
    @Builder
	public TokenDto(String grantType, String accessToken, String refreshToken, Long accessTokenExpiresIn) {
		super();
		this.grantType = grantType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	@Override
	public String toString() {
		return "TokenDto [grantType=" + grantType + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken
				+ ", accessTokenExpiresIn=" + accessTokenExpiresIn + "]";
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getAccessTokenExpiresIn() {
		return accessTokenExpiresIn;
	}

	public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}


	
}