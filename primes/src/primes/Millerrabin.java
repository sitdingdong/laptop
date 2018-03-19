package primes;

import java.util.Random;

public class Millerrabin {
	//public static Random r = new Random();

	public static int powermod(int x, int n, int m) {
		int prod = 1;
		while(n > 0) {
			if(n % 2 != 0) {
				prod = prod * x % m;
			}
			x = x * x % m;
			n /= 2;
		}
		return prod;
	}
	public static boolean millerRabin(int p, int k) {
		Random rnd = new Random();
		int r = 0;
		int tp = p - 1;
		while(tp % 2 != 0) {
			r++;
			tp /= 2;
		}
		int d = tp;
		for(int i = 0; i < k; ++i) {
			int a = rnd.nextInt(p - 3) + 2;
			int x = powermod(a, d, p);
			if(x == 1 || x == p - 1) {
				continue;
			}
			boolean c = false;
			for(int j = 0; j < r - 1; j++) {
				x = powermod(x, 2 , p);
				if(x == 1) {
					return false;
				}
				if(x == p - 1) {
					c = true;
					break;
				}
			}
			if(!c) return false;
		}
		return true;
	}
	public static void main(String[] args) {

			System.out.print((millerRabin(7, 100) ?   " is probably prime\n" : "not"));

	}

}
