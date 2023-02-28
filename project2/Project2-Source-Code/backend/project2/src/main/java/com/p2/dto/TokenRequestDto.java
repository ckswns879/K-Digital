package com.p2.dto;



public class TokenRequestDto { //TokenRequestDto 에는 재발급을 위한 AccessToken / RefreshToken String 이 존재합니다.
    private String accessToken;
    private String refreshToken;
    public TokenRequestDto() {
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "TokenRequestDto [accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
	}
	public TokenRequestDto(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}