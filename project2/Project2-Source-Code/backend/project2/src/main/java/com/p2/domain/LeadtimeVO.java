package com.p2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeadtimeVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String item;		//청구품목
	private int leadtime;		//리드타임
	private String carrier;		//출고운반선

	public LeadtimeVO() {
		// TODO Auto-generated constructor stub
	}

	public LeadtimeVO(String item, int leadtime, String carrier) {
		super();
		this.item = item;
		this.leadtime = leadtime;
		this.carrier = carrier;
	}

	@Override
	public String toString() {
		return "LeadtimeVO [item=" + item + ", leadtime=" + leadtime + ", carrier=" + carrier + "]";
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getLeadtime() {
		return leadtime;
	}

	public void setLeadtime(int leadtime) {
		this.leadtime = leadtime;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

}
