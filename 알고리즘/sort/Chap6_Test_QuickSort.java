package 자바자료구조;

public class Chap6_Test_QuickSort {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void quickSort(int[] a, int left, int right) {

		Stack3<Point> st = new Stack3<Point>(a.length);
		Point pt = new Point(left, right);
		st.push(pt);
		
		while (st.isEmpty() != true) {
    		Point rt = st.pop();
    		int pl = rt.getX();
    		int pr = rt.getY();
    		int nextPl = pl;
    		int nextPr = pr;
    		int x = a[(pr + pl)/2];		//피벗값(기준점)
    		
	        do {							//피벗을 기준으로 왼쪽(작은값) 오른쪽(큰값)으로나눔
	        	while (a[pl] < x) pl++;		// a[pl] < x 성립할때까지 pl++로 배열이동 
	          	while (a[pr] > x) pr--;		// a[pr] > x 성립할때까지 pr--로 배열이동
	          	if (pl <= pr)
	              swap(a, pl++, pr--);		//pl++, pr--로 pl값과 pr값을교체
	        } while (pl <= pr);
	        if (nextPl < pr) {				//작은값 푸시
	        	Point pt2 = new Point(nextPl, pr);
	        	st.push(pt2);
		    }
		    if (pl < nextPr) {				//큰값 푸시
		       	Point pt3 = new Point(pl, nextPr);
		    	st.push(pt3);
		    }
		}
		
	}


	public static void main(String[] args) {
		int nx = 10;
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();			//20보다작은 난수10개생성
			x[ix] = (int) (d * 20);
		}
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
		System.out.println();

		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
	}
}

