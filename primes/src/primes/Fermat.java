package primes;

import java.util.Random;

public class Fermat {
	public static int powermod(int x,int n, int m) {
		int prod = 1;
		while(n > 0) {
			if(n % 2 != 0) {
				prod = prod * x % m;
			}
			x = x * x % m;
			n = n / 2;
		}
		return prod;
		
	}
	public static boolean Fermats(int p,int k) {
		Random rad = new Random();
		for(int i = 1; i <= k; i++) {
			int a = rad.nextInt(p - 2) + 2;
			if(powermod(a, p-1, p) != 1) {
				return false;
			}
		}
		return true;
	}
	


}
