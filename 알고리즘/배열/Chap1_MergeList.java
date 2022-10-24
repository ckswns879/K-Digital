package 자바자료구조;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chap1_MergeList {
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
	    int n1 = list1.size();
	    int n2 = list2.size();
	    
	    while(i < n1 && j < n2) {
    		if((list1.get(i)).compareTo(list2.get(j)) < 0) {
    			list3.add(list1.get(i));
    			i++;
    		} else {
				list3.add(list2.get(j));
    			j++;
    		}
	    }
	    
	    while(i < n1 && j == n2) {
	    	list3.add(list1.get(i));
			i++;
	    }
	    while(i == n1 && j < n2) {
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
	    System.out.print("\n\nremoveDuplicate");
	    System.out.println("\nlist1::");
	    for(String city: list1) {
	    	System.out.print(city+ " ");
	    }
	    System.out.println("\nlist2::");
	    for(String city: list2) {
	    	System.out.print(city+ " ");
	    }
	    
//	     merge
	    List<String> list3 = new ArrayList<String>();
	    list3 = mergeList(list1, list2, list3);
	    System.out.print("\n\nmergeList");
	    System.out.println("\nlist3::");
	    for(String city: list3) {
	    	System.out.print(city+ " ");
	    }
	    

	}
	
}

