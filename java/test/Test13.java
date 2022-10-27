package edu;

public class Test13 {
	public static void main(String[] args) {
		byte a = 0b00010001; // 2 진수 표현은 0b로 시작함
		byte b = 0b00100010;

		int c = a & b; // 0
		System.out.println(Integer.toBinaryString(c));

		int d = a | b; // 110011
		System.out.println(Integer.toBinaryString(d));

		int e = a ^ b; // 110011
		System.out.println(Integer.toBinaryString(e));

		int f = 12;
		int g = (~f) + 1; // -12
		System.out.println(g);

		int h = f << 1; // 24
		int i = f >> 1; // 6
		System.out.println(h);
		System.out.println(i);
	}
}