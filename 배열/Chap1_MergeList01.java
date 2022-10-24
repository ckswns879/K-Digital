package 자바자료구조;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Chap1_MergeList01 {
	public static String[] removeElement(String[] arr, String item) {
		List<String> list = new ArrayList<>(Arrays.asList(arr));
		list.remove(item);
		return list.toArray(String[]::new);
	}
	
	public static List<String> removeDuplicate(String[] arr) {
		int count = arr.length;
		for (int i = 0; i < count; i++) {
			int j = i + 1;
			while (j < count) {
				if((arr[i]).compareTo(arr[j]) == 0) {
					arr = removeElement(arr, arr[j]);
					count--;
				} else {
					j++;
				}
			}
		}
		return Arrays.asList(arr);
	}
	
	public static List<String> mergeList(List<String> list1, List<String> list2, List<String> list3) {
		int i = 0;
	    int j = 0;
	    int k = 0;    
	    
	    
	    while(i < list1.size() && j < list2.size()) {
    		if((list1.get(i)).compareTo(list2.get(j)) < 0) {
    			list3.add(list1.get(i));
    			i++;
    		} else {
				list3.add(list2.get(j));
    			j++;
    		}
	    }
	    
	    while(i < list1.size() && j == list2.size()) {
	    	list3.add(list1.get(i));
			i++;
	    }
	    while(i == list1.size() && j < list2.size()) {
	    	list3.add(list2.get(j));
			j++;
	    }
	    
	    String[] arr3 = new String[0];
	    arr3 = list3.toArray(arr3);
	    list3 = removeDuplicate(arr3);
	    
	    return list3;
	}
	
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
			list1.add("서울");
			list1.add("북경");
			list1.add("상해");
			list1.add("서울");
			list1.add("도쿄");
			list1.add("도쿄");
			list1.add("뉴욕");

		List<String> list2 = new ArrayList<String>();
			list2.add("런던");
			list2.add("런던");
			list2.add("로마");
			list2.add("방콕");
			list2.add("북경");
			list2.add("북경");
			list2.add("도쿄");
			list2.add("서울");
			list2.add(1, "LA");
			
			
		    System.out.println("addAll() 메서드 호출 전");
		    System.out.println(list1);

		    list1.addAll(list2);
			Collections.sort(list1);
		    System.out.println("addAll() 메서드 호출 후");
		    System.out.println(list1);
		    
		
		// list sort한 후 출력
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
	    
	    // list to array
	    String[] arr1 = new String[0];
	    arr1 = list1.toArray(arr1);
	    String[] arr2 = new String[0];
	    arr2 = list2.toArray(arr2);
	    
	    // 중복제거
	    list1 = removeDuplicate(arr1);
	    list2 = removeDuplicate(arr2);
	    
	    // 중복제거 후 출력
	    System.out.print("removeDuplicate");
	    System.out.println("\nlist1 : ");
	    for(String city: list1) {
	    	System.out.print(city+ " ");
	    }
	    System.out.println("\nlist2 : ");
	    for(String city: list2) {
	    	System.out.print(city+ " ");
	    }
	    

	    
//	     merge
	    List<String> list3 = new ArrayList<String>();
	    list3 = mergeList(list1, list2, list3);
	    
	    System.out.print("\nmergeList \nlist3 : ");
	    Collections.sort(list3);
	    for(String city: list3) {
	    	System.out.print(city+ " ");
	    }
	    
	    
	    
	    Iterator<String> iter1 = list1.iterator();
	    Iterator<String> iter2 = list2.iterator();
	    String s1 = iter1.next();
	    String s2 = iter2.next();
	    while(iter1.hasNext() &&iter2.hasNext()) {
	    	if((s1.compareTo(s2))<0) {
	    		list3.add(s1);
	    		s1 = iter1.next();
	    	}
	    	else if ((s2.compareTo(s1))<0) {
	    		list3.add(s2);
	    		s2 = iter2.next();
	    	}
	    	else {
	    		list3.add(s1);
	    		s1 = iter1.next();
	    		s2 = iter2.next();
	
	    	}
	    	
	    }
	    while (iter1.hasNext()) {
	    	if((s1.compareTo(s2))<0) {
	    		list3.add(s1);
	    		s1 = iter1.next();
	    	} else if ((s1.compareTo(s2))>0) {
	    		list3.add(s2);
	    		while (iter1.hasNext()) {
	    			list3.add(s1);
	    			s1 = iter1.next();
	    		}
	    		list3.add(s1);
	    	} else if ((s1.compareTo(s2))==0) {
	    		while (iter1.hasNext()) {
	    			list3.add(s1);
	    			s1 = iter1.next();
	    		}
	    		list3.add(s1);
	    	}
	    	
	    
	    	while (iter2.hasNext()) {
	    		if((s2.compareTo(s1))<0) {
		    		list3.add(s2);
		    		s2 = iter2.next();
		    	} else if ((s2.compareTo(s1))>0) {
		    		list3.add(s1);
		    		while (iter2.hasNext()) {
		    			list3.add(s2);
		    			s2 = iter2.next();
		    		}
		    		list3.add(s2);
		    	} else if ((s1.compareTo(s2))==0) {
		    		while (iter1.hasNext()) {
		    			list3.add(s2);
		    			s2 = iter2.next();
	    	}
		    		list3.add(s2);
		    	}
	     
	    	}
	    }
	    System.out.println();
	    System.out.println("merge:: ");
	    for(String city: list3) 
	    	System.out.print(city+ " ");
	    
	}
}
