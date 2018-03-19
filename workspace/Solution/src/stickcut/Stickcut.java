package stickcut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stickcut {
	public static List<Integer> stickcut(int[] a) {
		Arrays.sort(a);
		List<Integer> ls = new ArrayList<>();
		int check = a[0];
		int cut = 1;
		int length = a.length;
		ls.add(length);
		for (int i = 1; i < a.length; i++) {
			if(a[i] == check){
				cut ++ ;
			}
			if(a[i] > check){
				length = length - cut;
				ls.add(length);
				check = a[i];
				cut = 1;		
			}
			
		}
		return ls;
	}
	public static List<Integer> stickcut1(int[] a) {
		Arrays.sort(a);
		List<Integer> ls = new ArrayList<>();
		ls.add(a.length);
		for (int i = 1; i < a.length; i++) {
			if(a[i] != a[i - 1]) ls.add(a.length - i);
		}
		return ls;
	}
	public static void main(String[] args) {
		int[] a = new int[] {1,1,2,3,4,5};
		List<Integer> ls = stickcut(a);
		for(Integer e : ls) {
			System.out.print(e + "  ");
		}
	}

}
