package primes;

public class GCD {
	public static int gcd(int a, int b) {
		for(int i = Math.min(a, b); i > 2; i--) {
			if(a % i ==0 && b % i == 0) {
				return i;
			}
		}
		return 1;
	}
	public static int lcm(int a, int b) {
		return a * b / gcd(a,b);
	}
	public static void main(String[] args) {
		System.out.println(gcd(20,0));
	}

}
