package 자바자료구조;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Chap1_merge_assignment {
	public static void main(String[] args) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add("서울");
				list1.add("북경");
				list1.add("상해");
				list1.add("서울");
				list1.add("도쿄");
				list1.add("뉴욕");

				ArrayList<String> list2 = new ArrayList<String>();
				list2.add("런던");
				list2.add("로마");
				list2.add("방콕");
				list2.add("북경");
				list2.add("도쿄");
				list2.add("서울");
				list2.add(1, "LA");
				
				//
				System.out.println("collection.sort()::");
			    Collections.sort(list1);
			    System.out.println("list1::");
			    for ( String city: list1)
			    	System.out.print(city+ " ");
			    System.out.println();
			    System.out.println("list2::");
			    Collections.sort(list2);
			    for ( String city: list2)
			    	System.out.print(city+ " ");
				ArrayList<String> list3 = new ArrayList<String>();
				
				Iterator<String> iter1 = list1.iterator();
				while(iter1.hasNext()) {
					String obj = (String)iter1.next();
					list3.add(obj);
				}
				
				Iterator<String> iter2 = list2.iterator();
				while(iter2.hasNext()) {
					String obj = (String)iter2.next();
					list3.add(obj);
				}
				Collections.sort(list3);				//정렬
				
				System.out.println();
			    System.out.println("merge:: ");
			    for ( String city: list3)
			    	System.out.print(city+ " ");
			    
			}	
}
