package edu;

public class Test37 {
	public static void main(String[] args) {
		int[] score = { 90, 85, 78, 100, 98 };
		int sum = 0; // ÃÑÁ¡
		double avg = 0.0; // Æò±Õ
		int max = 0; // ÃÖ´ñ°ª
		int min = 999; // ÃÖ¼Ú°ª

		for (int i = 0; i < score.length; i++) {
			sum += score[i];
			if (max < score[i])
				max = score[i];
			if (min > score[i])
				min = score[i];
		}

		avg = sum / score.length;
		System.out.println("ÃÑ Á¡ : " + sum);
		System.out.println("Æò ±Õ : " + avg);
		System.out.println("ÃÖ´ñ°ª : " + max);
		System.out.println("ÃÖ¼Ú°ª : " + min);
	}
}