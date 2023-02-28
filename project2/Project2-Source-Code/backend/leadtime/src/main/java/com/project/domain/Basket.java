package com.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Basket {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private int id;
	private String key2;
	private String machinery;
	private String items;
	private String part1;
	private int leadtime;
	private String baljucheo;
	private String gyeonjeokhwapye;
	private int gyeonjeokdanga;
	
}
