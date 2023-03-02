package com.project.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class ItemInfo {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private Long id;
	private String subjects;		//subject
	private String machinery;		//machinery
	private String assembly;		//assembly
	private String items;			//청구품
	private String part1;			//part1
	private String part2;			//part2
	private String key2;			//key2
	private String leadtime;		//리드타임
	private String clients;			//발주처
	private String esti_unit_price;	//견적단가
	private String currency;		//견적화폐
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore	
    private List<Payment> payment;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
    private List<Basket> basket;
	
	//items를 조회할 때 payment list의 item 필드가 다시 이 entity를 조회하면서 순환참조 발생
	//@JsonIgnore로 참조를 방지해버림
	//dto를 사용하는 것이 더 좋은 방법(이유 확인 요망)이라서 dto를 사용한 방식으로 수정 필요

}
