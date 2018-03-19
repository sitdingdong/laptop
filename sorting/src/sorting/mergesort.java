package sorting;

import java.util.Arrays;

public class mergesort {
	public static void mergesorts(int[] a, int L, int R) {
		if(L < R) {
			int mid = (L + R) / 2;
			mergesorts(a, L, mid);
			mergesorts(a, mid + 1, R);
			merge(a, L, mid, R);
		}
		
	}
	public static void merge(int[] a, int l, int mid, int r) {
		int[] temp = new int[r - l + 1];
		int m = l;
		int n = mid + 1;
		int s = 0;
		while(m <= mid && n <= r) {
			if(a[m] >= a[n]) temp[s++] = a[n++];
			else temp[s++] = a[m++];
		}
		while(m <= mid) temp[s++] = a[m++];
		while(n <= r) temp[s++] = a[n++];
		for(int i = 0; i < temp.length; i++) {
			a[i + l] = temp[i];
		}
	}
	public static void main(String[] args) {
		int []arr = {9,8,7,6,5,4,3,2,1};
        mergesorts(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
	}
	

}
