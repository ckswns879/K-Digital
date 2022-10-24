package 자바자료구조;

import java.util.Arrays;
import java.util.Comparator;

public class Test001 {
	
	class student implements Comparable<student>{
		private String name;
		private int age;
		
		public int CompareTo(student st) {
			if((this.name.compareTo(st.name))<0)return -1;
			else if((this.name.compareTo(st.name))>0)return 1;
			else return 0;
		}
		
		public student(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "student [name=" + name + ", age=" + age + "]";
		}
		
	}

	public static void main(String[] args) {
		
		student[] arr= {
				new student("박씨",10),
				new student("이씨",15),
				new student("김씨",9),
				new student("신씨",20)
		};
		Arrays.sort(arr, new Comparator<student>() {
		    @Override
		    public int compare(student o1, student o2) {
		        return o2.compareTo(o1);            // 내림차순으로 정렬
		    }
		});

		    System.out.println();
		    for ( student city: arr)
		    	System.out.print(" " + city);

	}

}
