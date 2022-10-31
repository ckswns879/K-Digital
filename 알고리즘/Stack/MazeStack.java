package 자바자료구조;

class Position {
    private int x;
    private int y;
    private int dir;
    
    public Position(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDir() {
    	return dir;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setDir(int dir) {
    	this.dir = dir;
    }
}

public class MazeStack {
    private Position[] stk; // 스택용 배열
    private int capacity; // 스택의 크기
    private int ptr; // 스택 포인터

    // --- 실행시 예외 : 스택이 비어있음 ---//
    public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() {
        }
    }

    // --- 실행시 예외 : 스택이 가득 참 ---//
    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() {
        }
    }

    // --- 생성자(constructor) ---//
    public MazeStack(int maxlen) {
        ptr = 0;
        capacity = maxlen;
        try {
            stk = new Position[capacity]; // 스택 본체용 배열을 생성
        } catch (OutOfMemoryError e) { // 생성할 수 없음
            capacity = 0;
        }
    }

    // --- 스택에 x를 푸시 ---//
    public Position push(Position p) throws OverflowIntStackException {
        if (ptr >= capacity) // 스택이 가득 참
            throw new OverflowIntStackException();
        return stk[ptr++] = p;
    }

    // --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
    public Position pop() throws EmptyIntStackException {
        if (ptr <= 0) // 스택이 빔
            throw new EmptyIntStackException();
        return stk[--ptr];
    }

    // --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
    public Position peek() throws EmptyIntStackException {
        if (ptr <= 0) // 스택이 빔
            throw new EmptyIntStackException();
        return stk[ptr - 1];
    }

    // --- 스택을 비움 ---//
    public void clear() {
        ptr = 0;
    }

    // --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
    public int size() {
        return ptr;
    }

    // --- 스택이 비어있는가? ---//
    public boolean isEmpty() {
        return ptr <= 0;
    }

    // --- 스택이 가득 찼는가? ---//
    public boolean isFull() {
        return ptr >= capacity;
    }

    // --- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
    public void dump() {
        if (ptr <= 0)
            System.out.println("스택이 비어있습니다.");
        else {
            for (int i = 0; i < ptr; i++) {
                System.out.printf("(%d, %d)",stk[i].getX(),stk[i].getY());
                if(i < ptr - 1) {
                	System.out.print(" -> ");
                }
                if((i + 1) % 5 == 0) {
                	System.out.println();
                }
            }
        }
    }
}