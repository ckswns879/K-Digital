package com.ruby.paper.domain;

public class VehicleVO {

	private String car_num; // 차량번호
	private int DRS; // 운행점수
	private int VRS; // 차량점수
	private int TS; // 종합점수
	private int Judgment; // 판정

	public VehicleVO() {
		super();
	}

	public VehicleVO(String car_num, int dRS, int vRS, int tS, int judgment) {
		super();
		this.car_num = car_num;
		DRS = dRS;
		VRS = vRS;
		TS = tS;
		Judgment = judgment;
	}

	public String getCar_num() {
		return car_num;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public int getDRS() {
		return DRS;
	}

	public void setDRS(int dRS) {
		DRS = dRS;
	}

	public int getVRS() {
		return VRS;
	}

	public void setVRS(int vRS) {
		VRS = vRS;
	}

	public int getTS() {
		return TS;
	}

	public void setTS(int tS) {
		TS = tS;
	}

	public int getJudgment() {
		return Judgment;
	}

	public void setJudgment(int judgment) {
		Judgment = judgment;
	}

	@Override
	public String toString() {
		return "VehicleVO [car_num=" + car_num + ", DRS=" + DRS + ", VRS=" + VRS + ", TS=" + TS + ", Judgment="
				+ Judgment + "]";
	}
}
