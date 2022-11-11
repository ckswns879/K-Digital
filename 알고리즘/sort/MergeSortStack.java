package 자바자료구조;

public class MergeSortStack {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(int[] a, int lefta, int righta, int leftb, int rightb) {
		int p = lefta, q = leftb; 	// 왼쪽배열?을p (lefta~righta) 오른쪽배열을q (leftb~rightb)
		int[] t = new int[20];		// p,q 를담을 배열 t[]를생성
		int ix = 0;					//t[ix] t[] 0부터시작
		while (p <= righta && q <= rightb) { // 작은값을넣고 ++
			if (a[p] >= a[q]) {
				t[ix] = a[q];
				q++;
				ix++;
			} else if (a[p] < a[q]) {
				t[ix] = a[p];
				p++;
				ix++;
			}
		}
		while (p <= righta) { // q다넣고 남은 p값을 작은수 먼저 t[ix]에넣음
			t[ix] = a[p];
			p++;
			ix++;
		}
		while (q <= righta) { // p다넣고 남은 q값을 작은수 먼저 t[ix]에넣음
			t[ix] = a[q];
			q++;
			ix++;
		}
		p = lefta;
		for (int j = 0; j < ix; j++) { // 정렬된 임시배열(t[])를 기존의 배열(a[])에 복사
			a[p] = t[j];
			System.out.print(" a[]= " + a[p]);
			p++;
		}
		System.out.println();
	}
	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(int[] a, int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2; // middle(피벗값)을정함
			MergeSort(a, left, middle); // left~middle값까지를 정렬
			MergeSort(a, middle + 1, right); // middle+1(middle값이후)~right값까지를 정렬
			merge(a, left, middle, middle + 1, right); // 정렬한두값을 합침(merge)

		}
	}

	public static void main(String[] args) {
		System.out.println("난수생성");
		int nx = 20;
		int[] x = new int[20]; // 50보다작은난수20개생성
		for (int ix = 0; ix < 20; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 50);
		}
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
		System.out.println();

		MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
	}
}
