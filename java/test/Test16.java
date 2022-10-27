package edu;

public class Test16 {
	public static void main(String[] args) {
		byte a = 23;
		int b = a;
		System.out.println(b); // 23

		byte c = 23;
		short d = 47;
		int e = 65;
		int f = c + d + e;
		System.out.println(f); // 135

		int g = 23;
		byte h = (byte) g;
		System.out.println(h); // 23

		byte i = 10;
		byte j = 20;
		byte k = (byte) (i + j);
		System.out.println(k); // 30
	}
}
