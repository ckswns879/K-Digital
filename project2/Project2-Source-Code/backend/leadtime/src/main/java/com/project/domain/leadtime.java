package com.project.domain;

import java.util.Date;

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
public class leadtime {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String machinery;
	private String items;
	private String part1;
	private String key2;
	private Date balju;
	private String baljucheo;
	private Date changgoipgo;
	private int gyeonjeokdanga;
	private String assembly;
	private Date gyeonjeok;
	private String gyeonjeokhwapye;
	private int dt;
	private int leadtime;

}
