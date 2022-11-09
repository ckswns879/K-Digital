package 자바자료구조;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}

	@Override
	public String toString() {
		return "P(" + ix + ", " + iy + ")";
	}
}

public class Stack3<T> {
// p. 133 코드를 General을 활용하여 리스트로 고치기

	/*
	 * 제네릭클래스는 상속 불가 // 빈스택 예외처리 public class EmptyStakException extends Exception {
	 * private static final long serialVersionUID = 1L; public void
	 * EmptyStackException() { super(); } } // 풀스택 예외처리 public class
	 * OverflowStackException extends RuntimeException { public
	 * OverflowStackException() { } }
	 */

	// private T data[];
	private List<T> data;
	private int capacity;
	private int top;

	// 생성자(constructor)
	public Stack3() {
		top = 0;
		capacity = 0;
		data = new ArrayList<>(Collections.emptyList());
	}
	
	@Override
	public String toString() {
		return "Stack [data=" + data + ", capacity=" + capacity + ", top=" + top + "]";
	}

	public Stack3(int maxlen) {
		top = 0;
		capacity = maxlen;
		data = new ArrayList<>(Collections.emptyList());
	}

	// 스택에 x를 push
	public void push(T x) {
		if (top < capacity) {
			++top;
			// list 사이즈 확장
			data.add(x);
			// list 원소로 x를 넣어주기
		}
	}

	/*
	 * UnsupportedOperationException 일반적으로 List 형을 new로 초기화하지 않는 상태에서 Arrays로 생성하였을
	 * 시 주로 발생
	 */
	// 스택에서 데이터를 pop(정상에 있는 데이터를 꺼냄)
	public T pop() {
		// pop하는 값을 출력해주기 위해 return 사용
		T temp = null;
		if (top != 0) {
			temp = data.get(top - 1);
			data.remove(--top);
		}
		return temp;
	}

	// 스택에서 데이터를 peek(정상에 있는 데이터를 들여다봄)
	public T peek() {
		return data.get(top - 1);
		// top은 list.size와 같음 > index = top-1
		// peek하는 값을 출력해주기 위해 return 사용
	}

	// 스택을 비움
	public void clear() {
		for(int i=0; i<data.size(); i++) {
			data.remove(i);
		}
		top = 0;
		/*
		 * 스택의 요소값은 스택 포인터를 기준으로 처리되므로 스택 포인터를 0으로 주면 모두 삭제됨
		 */
	}

	// 스택에서 x를 찾아 인덱스(없으면 -1) 반환
	public int indexOf(T x) {
		for (int i = top - 1; i >= 0; i--) {
			if (data.get(i) == x)
				return i;
		}
		return -1;
	}

	// 스택의 크기 반환
	public int getCapacity() {
		return capacity;
	}

	// 스택에 쌓여있는 데이터 갯수 반환
	public int size() {
		return top;
	}

	// 스택 비어있는지 확인
	public boolean isEmpty() {
		return top <= 0;
	}

	// 스택 가득찼는지 확인
	public boolean isFull() {
		return top >= capacity;
	}

	// 스택 안의 모든 데이터를 바닥 -> 정상 순서로 표시
	public void dump() {
		if (top <= 0) {
			System.out.println("스택이 비어있습니다.");
		} else {
			for (int i = 0; i < top; i++) {
				System.out.print(data.get(i) + " ");
			}
			System.out.println();
		}

	}
}