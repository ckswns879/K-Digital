package edu;

public class Test24 {
	public static void main(String[] args) {
		String nation = "KOR";

		switch (nation) {
		case "KOR":
		case "JPN":
		case "CHN":
			System.out.println("아시아");
			break;
		case "GBR":
		case "FRA":
		case "EUA":
			System.out.println("유럽");
			break;
		case "USA":
		case "CAN":
		case "MEX":
			System.out.println("아메리카");
			break;
		}
	}
}