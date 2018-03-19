package sorting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class quick {
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
	public static void quicksort(int[] a, int L, int R) {
		Random rnd = new Random();
		if(L >= R){
            return ;
        }
		if(L == R - 1) {
			if(a[L] > a[R]) {
				swap(a,L, R);
			}
			return;
		}
		int pivot = a[rnd.nextInt(R - L) + L] ;
		int l = L;
		int r = R;
		while (l < r) {
			if (r >= L && a[r] >=pivot) {
				r --;
			}
			if (l <= R && a[l] < pivot) {
				l ++;
			}
			if(l < r) swap(a,l, r);
		}
		quicksort(a, L, l - 1);
        quicksort(a, l, R); 
	}
	public static void swap(int[] a,int l, int r) {
		int temp = a[l];
	    a[l] = a[r];
	    a[r] = temp;
	}
	public static void main(String[] args) {
		int[] a = loaddata("hw3.dat");
		quicksort(a, 0, a.length - 1);
		for(int e : a) {
			System.out.print(e + " ");
		}
	}

}
