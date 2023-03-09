package com.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Payment {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_id")
	private Long id;
	private String item;		//청구품목
	private int leadtime;		//리드타임
	private String clients;		//발주처
	private int order_qty;		//발주수량
	private int order_price;	//발주금액
	
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false, updatable=false)
	private ItemInfo items;
	
	@ManyToOne
	@JoinColumn(name="member_id", nullable=false, updatable=false)
	private Member member;

}
