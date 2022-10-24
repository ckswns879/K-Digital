package 자바자료구조;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chap1_ArrayString {

	public static String[] removeElement1(String[] arr, String item) {
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        list.remove(item);
        return list.toArray(String[]::new);
    }
	public static void main(String[] args) {
		
//		String a = "hello", b="good";
//		String temp = a;
//		
//		a=b; b=temp;
//		System.out.println(a);
//		System.out.println(b);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("서울");
		list.add("북경");
		list.add("상해");
		list.add("서울");
		list.add("도쿄");
		list.add("뉴욕");


		list.add("런던");
		list.add("로마");
		list.add("방콕");
		list.add("북경");
		list.add("도쿄");
		list.add("서울");

		list.add(1, "LA");
		
		//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩
//		System.out.println("collection.sort()::");
//	    Collections.sort(list);
//	    for ( String city: list)
//	    	System.out.println(city);
	    
	    String cities[] = new String[0];
	    cities = list.toArray(cities);			//list를 배열로만듬(toArray) / 배열(.Array)을 list로만듬(.asList)
	    

//	    System.out.println("정렬후::");
//	    for ( String city: cities)
//	    	System.out.println(city);

	    
//	    String temp;
//	    for(int i = 0; i < cities.length; i++) {
//	    	for(int j = i+1; j < cities.length; j++) {
//	    		if(cities[i].compareTo(cities[j]) > 1){
//					 temp = cities[i];
//					cities[i] =  cities[j];
//					cities[j] = temp;
//				}
//
//	    	}
//	    }
//	    for(String citie:cities){
//			System.out.println(citie + "");
//
//	    }
//	    System.out.println("=================================");
//	    
//	    cities[0].
//	    //중복제거
//	    System.out.println("중복제거::");
//	    int m = 0, n = 1;
//
	    
       ArrayList<String> lst = new ArrayList<String>(Arrays.asList(cities));
       for(int k = 0; k < lst.size(); k++) {
    	   for(int s = k+1; s < lst.size(); s++) {
    		    if (lst.get(k).equals(lst.get(s))) {
                      lst.remove(k);
    		   }
    	   }

    	}
       for(String citie:lst) {
       System.out.println(citie);
       }
       
//	    for ( String city: lst)
//	    	System.out.println(city);
//	
//	    System.out.println(list);
//	    Collections.sort(list);
//	    System.out.println(list);
	    
	}
}
