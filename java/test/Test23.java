package edu;

public class Test23 {
	public static void main(String[] args) {
		char grade = 'F';

		switch (grade) {
		case 'A':
			System.out.println("90�� �̻�");
			break;
		case 'B':
			System.out.println("80�� �̻�");
			break;
		case 'C':
			System.out.println("70�� �̻�");
			break;
		case 'D':
			System.out.println("60�� �̻�");
			break;
		default:
			System.out.println("60�� �̸�");
			break;
		}
	}
}