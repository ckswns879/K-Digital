package com.p2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;


@Entity
public class RefreshTokenVO {

	@Id
	@Column(name = "rt_key")
	private String key;

	@Column(name = "rt_value")
	private String value;

	public RefreshTokenVO() {
		// TODO Auto-generated constructor stub
	}
	@Builder
	public RefreshTokenVO(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "RefreshTokenVO [key=" + key + ", value=" + value + "]";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public RefreshTokenVO updateValue(String refreshToken) {
        this.value = refreshToken;
        return this;
	}

}
