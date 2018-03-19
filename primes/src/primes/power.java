package primes;

import java.util.Random;

public class power {
	public static int power(int i,int m) {
		int prod = 1;
		while(m > 0) {
			if(m % 2 != 0) {
				prod = prod * i;
			}
			i *= i;
			m /= 2;
		}
		return prod;
	}
	public static int powmd(int i, int m, int n) {
		int prod = 1;
		while(m > 0) {
			if(m % 2 != 0) {
				prod = prod * i % n;
			}
			i = i * i % n;
			m /= 2;
		}
		return prod;
	}
	public static boolean fmt(int p, int k) {
		Random r = new Random();
		for(int i = 0; i < k; ++i) {
			int a = r.nextInt(p - 3) + 2;
			if(powmd(a, p - 1, p) != 1) return false; 
		}
		return true;
	}

}
