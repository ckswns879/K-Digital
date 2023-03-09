package com.p2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PaymentLogVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long id;
	private String item; // 청구품목
	private String invoice; // 청구서번호
	private String leadtime; // 리드타임
	private String clients; // 발주처
	private int order_quantity; // 발주수량
	private int order_amount; // 발주금액

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false, updatable = false)
	private ItemInfoVO items;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false, updatable = false)
	private MemberVO member;

	public PaymentLogVO() {
		// TODO Auto-generated constructor stub
	}

	public PaymentLogVO(long id, String item, String invoice, String leadtime, String clients, int order_quantity,
			int order_amount) {
		super();
		this.id = id;
		this.item = item;
		this.invoice = invoice;
		this.leadtime = leadtime;
		this.clients = clients;
		this.order_quantity = order_quantity;
		this.order_amount = order_amount;
	}

	@Override
	public String toString() {
		return "PaymentLogVO [id=" + id + ", item=" + item + ", invoice=" + invoice + ", leadtime=" + leadtime
				+ ", clients=" + clients + ", order_quantity=" + order_quantity + ", order_amount=" + order_amount
				+ "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getLeadtime() {
		return leadtime;
	}

	public void setLeadtime(String leadtime) {
		this.leadtime = leadtime;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public int getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}

}
