package com.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Basket {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String item;			//청구품목
	private int leadtime;			//리드타임
	private int billing_amount;		//청구량
	private String clients;			//발주처
	private int esti_unit_price; 	 //견적단가
	
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false, updatable=false)
	private ItemInfo items;
	
	@ManyToOne
	@JoinColumn(name="member_id", nullable=false, updatable=false)
	private Member member;

	
}
