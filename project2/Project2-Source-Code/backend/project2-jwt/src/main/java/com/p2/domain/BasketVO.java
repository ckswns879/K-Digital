package com.p2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BasketVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "basket_id")
	private Long id;
	private String email;
	private String item; // 청구품목
	private int leadtime; // 리드타임
	private int billing_amount; // 청구량
	private String clients; // 발주처

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false, updatable = false)
	private ItemInfoVO items;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false, updatable = false)
	private MemberVO member;

	public BasketVO() {
		// TODO Auto-generated constructor stub
	}

	public BasketVO(long id, String email, String item, int leadtime, int billing_amount, String clients) {
		super();
		this.id = id;
		this.email = email;
		this.item = item;
		this.leadtime = leadtime;
		this.billing_amount = billing_amount;
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "BasketVO [id=" + id + ", email=" + email + ", item=" + item + ", leadtime=" + leadtime
				+ ", billing_amount=" + billing_amount + ", clients=" + clients + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getBilling_amount() {
		return billing_amount;
	}

	public void setBilling_amount(int billing_amount) {
		this.billing_amount = billing_amount;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

}