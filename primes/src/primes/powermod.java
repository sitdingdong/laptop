package primes;

public class powermod {
	public static int power(int x,int n) {
		int prod = 1;
		while(n > 0) {
			if(n % 2 != 0) {
				prod = prod * x;
			}
			x = x * x;
			n = n / 2;
		}
		return prod;
	}
	public static int powermods(int x,int n, int m) {
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
	/* 
	 * int powermod(int x, int n, int m){
    int res = 1;
    while (n > 0){
        if (n % 2 == 1){
            res = res * x % m;
        }
        x = x * x % m;
        n /= 2;
    }
    return res;
}

	 * Fermat(p, k)
  for i ¡û 1 to k
    a ¡û random(2, p-1)
    if powermod(a, p-1, p) != 1
      return false
    end
  end
  return true (probably)
end

	 */
	
	public static void main(String[] args) {
		System.out.println(power(2,2));
	}

}
