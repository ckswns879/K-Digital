package com.p2.domain;

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

@Entity
public class ItemInfoVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;
	private String subjects; // Subject
	private String machinery; // Machinery
	private String assembly; // Assembly
	private String item; // 청구품목
	private String part1; // Part No.1
	private String part2; // Part No.2
	private String key2; // key2
	private int esti_unit_price; // 견적단가
	private String clients; // 발주처
	private String currency; // 견적화폐
	private int leadtime; // 리드타임

	@OneToMany(mappedBy = "items", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<PaymentLogVO> payment;

	@OneToMany(mappedBy = "items", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<BasketVO> basket;

	public ItemInfoVO() {
		// TODO Auto-generated constructor stub
	}

	public ItemInfoVO(Long id, String subjects, String machinery, String assembly, String item, String part1,
			String part2, String key2, int esti_unit_price, String clients, String currency, int leadtime,
			List<PaymentLogVO> payment, List<BasketVO> basket) {
		super();
		this.id = id;
		this.subjects = subjects;
		this.machinery = machinery;
		this.assembly = assembly;
		this.item = item;
		this.part1 = part1;
		this.part2 = part2;
		this.key2 = key2;
		this.esti_unit_price = esti_unit_price;
		this.clients = clients;
		this.currency = currency;
		this.leadtime = leadtime;
		this.payment = payment;
		this.basket = basket;
	}

	@Override
	public String toString() {
		return "ItemInfoVO [id=" + id + ", subjects=" + subjects + ", machinery=" + machinery + ", assembly=" + assembly
				+ ", item=" + item + ", part1=" + part1 + ", part2=" + part2 + ", key2=" + key2 + ", esti_unit_price="
				+ esti_unit_price + ", clients=" + clients + ", currency=" + currency + ", leadtime=" + leadtime
				+ ", payment=" + payment + ", basket=" + basket + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getMachinery() {
		return machinery;
	}

	public void setMachinery(String machinery) {
		this.machinery = machinery;
	}

	public String getAssembly() {
		return assembly;
	}

	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPart1() {
		return part1;
	}

	public void setPart1(String part1) {
		this.part1 = part1;
	}

	public String getPart2() {
		return part2;
	}

	public void setPart2(String part2) {
		this.part2 = part2;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public int getEsti_unit_price() {
		return esti_unit_price;
	}

	public void setEsti_unit_price(int esti_unit_price) {
		this.esti_unit_price = esti_unit_price;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getLeadtime() {
		return leadtime;
	}

	public void setLeadtime(int leadtime) {
		this.leadtime = leadtime;
	}

	public List<PaymentLogVO> getPayment() {
		return payment;
	}

	public void setPayment(List<PaymentLogVO> payment) {
		this.payment = payment;
	}

	public List<BasketVO> getBasket() {
		return basket;
	}

	public void setBasket(List<BasketVO> basket) {
		this.basket = basket;
	}

}
