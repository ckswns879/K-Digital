package edu;

public class Test24 {
	public static void main(String[] args) {
		String nation = "KOR";

		switch (nation) {
		case "KOR":
		case "JPN":
		case "CHN":
			System.out.println("�ƽþ�");
			break;
		case "GBR":
		case "FRA":
		case "EUA":
			System.out.println("����");
			break;
		case "USA":
		case "CAN":
		case "MEX":
			System.out.println("�Ƹ޸�ī");
			break;
		}
	}
}