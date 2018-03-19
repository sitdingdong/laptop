package sorting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class insertion {
	public static int[] insert(int[] a) {
		if(a.length < 2) {
			return a;
		}
		for (int i = 1; i < a.length; i++ ) {
			for (int j = i; j > 0; j --) {
				if(a[j] < a[j-1]) {
					int temp=a[j];
					a[j]=a[j-1];
					a[j-1]=temp;
				}
			}
		}
		return a;
		
		
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
		int[] b = insert(a);
		for(int e : b) {
			System.out.print(e + " ");
		}
		
	}

}
