package com.ruby.paper.domain;

public class DrivingVO {

	private int D_ID;	//인덱스
	private int date;	//운행일자
	private String car_num;	//차량 번호
	private int FSS;	//초당 과속 횟수
	private int PRP;	//양의 RPM 변화 횟수
	private int MRP;	//음의 RPM 변화 횟수
	private int RAC;	//급격한 가속 횟수
	private int SDS;	//급격한 감속 횟수
	private double DSR;	//운행당 안전 운전율
	private int DURS;	//운행당 운행점수
	private int VURS;	//운행당 차량점수
	
	public DrivingVO() {
		super();
	}

	public DrivingVO(int d_ID, int date, String car_num, int fSS, int pRP, int mRP, int rAC, int sDS, double dSR,
			int dURS, int vURS) {
		super();
		D_ID = d_ID;
		this.date = date;
		this.car_num = car_num;
		FSS = fSS;
		PRP = pRP;
		MRP = mRP;
		RAC = rAC;
		SDS = sDS;
		DSR = dSR;
		DURS = dURS;
		VURS = vURS;
	}

	public int getD_ID() {
		return D_ID;
	}

	public void setD_ID(int d_ID) {
		D_ID = d_ID;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getCar_num() {
		return car_num;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public int getFSS() {
		return FSS;
	}

	public void setFSS(int fSS) {
		FSS = fSS;
	}

	public int getPRP() {
		return PRP;
	}

	public void setPRP(int pRP) {
		PRP = pRP;
	}

	public int getMRP() {
		return MRP;
	}

	public void setMRP(int mRP) {
		MRP = mRP;
	}

	public int getRAC() {
		return RAC;
	}

	public void setRAC(int rAC) {
		RAC = rAC;
	}

	public int getSDS() {
		return SDS;
	}

	public void setSDS(int sDS) {
		SDS = sDS;
	}

	public double getDSR() {
		return DSR;
	}

	public void setDSR(double dSR) {
		DSR = dSR;
	}

	public int getDURS() {
		return DURS;
	}

	public void setDURS(int dURS) {
		DURS = dURS;
	}

	public int getVURS() {
		return VURS;
	}

	public void setVURS(int vURS) {
		VURS = vURS;
	}

	@Override
	public String toString() {
		return "DrivingVO [D_ID=" + D_ID + ", date=" + date + ", car_num=" + car_num + ", FSS=" + FSS + ", PRP=" + PRP
				+ ", MRP=" + MRP + ", RAC=" + RAC + ", SDS=" + SDS + ", DSR=" + DSR + ", DURS=" + DURS + ", VURS="
				+ VURS + "]";
	}	
}
