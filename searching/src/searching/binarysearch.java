package searching;

public class binarysearch {
	public static int search(int[] a, int target) {
		int begin = 0;
		int end = a.length - 1;
		int mid = (begin + end) / 2;
		while(begin <= end) {
			if(a[mid] == target) return mid;
			else if(a[mid] < target) begin = mid + 1;
			else end = mid - 1;
			mid = (begin + end) / 2;
		}
		return -1;
	}
	public static int recubs(int[] a, int target, int l, int r) {
		int mid = (l + r) / 2;
		if(a[mid] == target) return mid;
		if(l >= r) {
			return -1;
		}else if(a[mid] < target) { 
				return recubs(a, target, mid + 1, r);
			}
			else if(a[mid] < target) {
				return recubs(a, target, l, mid - 1);
			}
		return -1;	
		
	}
	public static void main(String[] args) {
		int[] a = {1, 3, 5, 6, 7, 9};
		int b = search(a, 5);
		System.out.println(b);
	}

}
