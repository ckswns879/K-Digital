package 자바자료구조;

public class Backtracking_Queen {

	public static void SolveQueen(int[][] d) {
		int count = 0, mode = 0;
		int ix = 0, iy = 0;
		Stack1 st = new Stack1(10);
		Point p = new Point(ix, iy);
		d[ix][iy] = 1;						//d[0][0]좌표를 1로설정하고 배치
		count++;
		st.push(p);
		while (count < d.length) {			
			ix++;				//행변경
			int cy = 0;			// 행변경 후 항상 0번째 열부터 체크 필요
			while (ix < d.length){
			cy = NextMove(d,ix,cy);
				if(cy != d.length) {	
					Point px = new Point(ix, cy);		
						st.push(px);	//이동이가능하면 이동된위치를푸시하고 count++
						d[ix][cy] = 1;						//이동가능한 d[ix][iy]좌표를 1로표시함
						System.out.println("ix= " + ix + " cy = "+ cy);
						count++;
						break;
													
				}
				if (cy != d[0].length) {			//이동위치가 범위를넘으면 멈춤
					break;
				} else {							//범위가 같으면 팝하고 0으로표시후cy++
				 p = st.pop();
				 		 
				 ix=p.getX();
				 cy=p.getY();
				 count--;
				 d[ix][cy] = 0;
				 cy++; 
				}
				}
			}
	
		}
	

	public static boolean checkRow(int[][] d, int crow) {	//행 검사 x값은고정 y값만 증가시키면서 검사
		for (int  i = 0; i < d.length; i++) {
			if (d[crow][i] == 1) {							//값이 1과같으면 false 1이아니면 true
				return false;
			}
		}
		return true;
	}
	public static boolean checkCol(int[][] d, int ccol) {	//열검사 y값은고정 x값만 증가시키면서 검사
		for (int i = 0; i <d.length; i++) {
			if (d[i][ccol] == 1 ) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDiagSW(int[][] d, int x, int y) { //x++, y-- or x--, y++ where 0<= x,y <= 7
		int cx = x;
		int cy = y;
		while(cx >= 0 && cy < d[0].length-1) { 	//대각선검사 
			
			if(d[cx][cy] == 1) {										//오른쪽위 검사
				return false;
				}
				cx--;
				cy++;
			}
		cx = x; cy = y;													//위에서-- ++해서 다시위치정함
		while(cy >= 0 && cx < d.length-1) {	//왼쪽아래 검사
			if(d[cx][cy] == 1) {
				return false;
			}
				cx++;
				cy--;
		}
		return true;
	}
	public static boolean checkDiagSE(int[][] d, int x, int y) {//x++, y++ or x--, y--
		int cx = x;
		int cy = y;
		while(cx >= 0 && cy >= 0) {	//왼쪽위 검사
			if(d[cx][cy] == 1) {										
			return false;
				}
				cx--;
				cy--;
			}
		 cx = x; cy = y;											//위에서-- --해서 다시위치정함
		while( cx < d.length - 1 && cy < d[0].length - 1) {	//오른쪽 아래 검사
			if(d[cx][cy] == 1) {										
			return false;
			}
			cx++;
			cy++;
		}
			return true;
	}
    public static boolean CheckMove(int[][]d, int x, int y) {//(x,y)로 이동 가능한지를 check
    	if (checkRow(d,x) && checkCol(d,y) && checkDiagSW(d, x, y) &&checkDiagSE(d, x, y)) 
    		return true;
    	return false;	
    }
    public static int NextMove(int[][]d, int row, int col) {//다음 row에 대하여 이동할 col을 조사하여 이동 가능하면 col을 리턴한다
    	// row는 고정이고 col 값만 이용하여 checkMove()
    	while (col < d.length) {
    		if (CheckMove(d,row,col)) {
    			return col;
    		}
    			col++;
    	}
    	
    	return d[0].length;
    }
	public static void main(String[] args) {			//체스판생성
		int row = 8, col = 8;
		int[][] data = new int[8][8];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		SolveQueen(data);

		for (int i = 0; i < data.length; i++) {			//데이터를적용받아 체스판출력
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(" " + data[i][j]);
			}
			System.out.println();
		}
	}
}
