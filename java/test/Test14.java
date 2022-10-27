package edu;

public class Test14 {
	public static void main(String[] args) {
		char c = 'A';
		c ^= (1 << 5);
		System.out.println(c);
	}
}