package 자바자료구조;

import java.util.Stack;

class Items {
	private int x, y, dir;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Items(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "Items [x=" + x + ", y=" + y + ", dir=" + dir + "]";
	}

}

class offsets {
	public int a, b;

		public offsets(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "offsets [a=" + a + ", b=" + b + "]";
	}

	public int a() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int b() {
		// TODO Auto-generated method stub
		return 0;
	}

}

public class MazingProblemCPP {

	enum directions {
		N, NE, E, SE, S, SW, W, NW
	};

	static offsets moves[] = new offsets[8]; // offsets 가 int역활을함
	static Items temp = new Items(0, 0, 0);
	static int maze[][] = new int[100][100];
	static int mark[][] = new int[100][100];

	static void path(int m, int p) {		//m,p는 출구
		
		mark[1][1] = 1;
		Stack2 st = new Stack2(100);

		temp.setX(1);
		temp.setY(1);
		temp.setDir(0);  		// move[0]쪽부터검사시작
		st.push(temp);
		
		System.out.println("초기:: " + temp.toString());
	
		while (!st.isEmpty()) // 스택이없을때까지확인
		{
			temp = st.pop(); // unstack?
			int i = temp.getX();			//x의현재위치
			int j = temp.getY();
			int d = temp.getDir();
			while (d < 8) // moves forward moves[0~7]
			{
				int g = i + moves[d].a;			//		g,h는 다음위치(이동된위치)			g = x +(- 1)
				int h = j + moves[d].b;												//  h = y +(0)
				
				if ((g == m) && (h == p)) { // reached exit 출구에도착하면
					st.push(temp);						// output path  출구위치 푸시
					
					return;
				}
				if ((maze[g][h] == 0) && (mark[g][h]) != 1) {  // 다음 위치가 0이거나 왔던길이 아니면
					mark[i][j] = 1;		// 다음 위치 발견하면를 현재위치 1로 mark에 저장
					
					Items tmp = new Items(i,j,d+1);	//
					temp.setX(i);
					temp.setY(j);
					temp.setDir(d);
					st.push(tmp); // stack it  현재위치저장푸쉬 >> 다음좌표로넘어가서(d++) 계산 
					i = g; 
					j = h; 
					d = 0; // moves to (g,h)   // 0으로설정
				}
				else d++; // dir++
			}
			
		}
		
}

	public static void main(String[] args) {

		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 }, 
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 }, 
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }, };
			for(int i=0;i<8;i++)
				moves[i] = new offsets(0,0);
		moves[0].a = -1;	//서쪽
		moves[0].b = 0;
		moves[1].a = -1;	//북서
		moves[1].b = 1;
		moves[2].a = 0;		//북
		moves[2].b = 1;
		moves[3].a = 1;		//북동
		moves[3].b = 1;
		moves[4].a = 1;		//동
		moves[4].b = 0;
		moves[5].a = 1;		//남동
		moves[5].b = -1;
		moves[6].a = 0;		//남
		moves[6].b = -1;
		moves[7].a = -1;	//남서
		moves[7].b = -1;
		
//벽 테두리 생성
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				if ((i == 0) || (j == 0) || (i == 13) || (j == 16))
					maze[i][j] = 1;
				else {
					maze[i][j] = input[i - 1][j - 1];
				}
				mark[i][j] = 0;

			}
		}
		System.out.println("maze[12,15]::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(maze[i][j] + " ");

			}
			System.out.println();
		}
		System.out.println("mark::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(mark[i][j] + " ");

			}
			System.out.println();
		}
		path(12, 15);
		System.out.println("mark::");
		for (int i = 1; i <= 12; i++) {
			for (int j = 1; j <= 15; j++) {
				System.out.print(mark[i][j] + " ");
				}
			System.out.println();
				}

	}
}