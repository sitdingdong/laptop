package sorting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class basicquick {
	public static int bqsort(int[] a, int l, int r) {
		int pivot = a[l];
		while(l < r) {
			while (l < r && a[r] >= pivot) {
				r --;
			}
			a[l] = a[r];
			while(l < r && a[l] < pivot) {
				l ++;
			}
			a[r] = a[l];
		}
		a[r] = pivot;
		return r;
	}
	public static void sort(int[] a, int L, int R) {
		if(L >= R){
            return ;
        }
        int index = bqsort(a, L, R);
        //System.out.println(L + " " + index + " " + R);
        sort(a, L, index -1);
        sort(a, index + 1, R); 
	}
	public static int[] loaddata(String name) {
		Scanner s = null;
		int a;
		try {
			s = new Scanner(new FileReader(name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		a = s.nextInt();
		int[] input = new int[a];
		for(int i =0; i < a; i++) {
			input[i] = s.nextInt();
		}
		return input;	
	}
	public static void main(String[] args) {
		int[] a = loaddata("hw3.dat");
		sort(a, 0, a.length - 1);
		for(int e : a) {
			System.out.print(e + " ");
		}
	}
}
