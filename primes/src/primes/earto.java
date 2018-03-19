package primes;

public class earto {
	public static boolean[] ear(int n) {
		boolean[] a = new boolean[n + 1];
		for(int i = 2; i < n + 1; i++) {
			a[i] = true;
		}
		for(int i = 2; i < n + 1; i++) {
			if(a[i]) {
				for(int j = i * i; j <= n; j += i) {
					a[j] = false;
				}
			}
		}
		return a;
	}
	public static boolean[] ear2(int n) {
		boolean[] p = new boolean[n + 1];
		for(int i = 2; i < n + 1; i++) {
			p[i] = true;
		}
		for(int i = 4; i < n + 1; i += 2) {
			p[i] = false;
		}
		for(int i = 3; i < n + 1; i += 2) {
			if(p[i]) {
				for(int j = i * i; j <= n; j += 2 * i) {
					p[j] = false;
				}
			}
		}
		return p;
	}
	public static void main(String[] args) {
		boolean[] b = ear2(10);
		for(int i = 0; i <b.length; i++) {
			if(b[i]) {
				System.out.print(i);
			}
		}
	}

}
