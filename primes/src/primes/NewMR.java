package primes;

import java.util.Random;

public class NewMR {
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
	public static boolean millerrabin(int p,int k) {
		Random rnd = new Random();
		int d;
		int r = 0;
		int tp = p - 1;
		while(tp % 2 == 0) {
			r++;
			tp /= 2;
		}
		d = tp;
		for(int i = 0; i < k; i++) {
			int a = rnd.nextInt(p - 3) + 2;
			int x = powermod(a, d, p);
			if(x == 1 || x == p - 1) {
				continue;
			}
			boolean contin = false;
			for(int j = 0; j < r - 1; j++) {
				x = powermod(x, 2, p);
				if(x == 1) {
					return false;
				}
				else if (x == p - 1) {
					contin = true;
					break;
				}
			}
			if(!contin) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		System.out.println((millerrabin(17,10)? "prime":"not"));
	}
	

}
/*
bool millerRabin(int p, int k){
int d, r = 0;
int tp = p -1;
while (tp % 2 == 0){
    r ++ ;
    tp /= 2;
}
d = tp;
for (int i = 0; i < k; ++i){
    int a = 2 + rand() % (p - 2);
    int x = powermod(a, d, p);
    if (x == 1 || x == p-1){
        continue;
    }
    bool to_continue = false;
    for (int j = 0; j < r-1; ++j){
        x = powermod(x, 2, p);

        if (x == 1){
            return false;
        }
        else if (x == p - 1){
            to_continue = true;
            break;
        }
    }
    if (!to_continue){
        return false;
    }
}
return true;
}
boolean mr(int p,int k) {
Random r = new Random();
int r;
int tp = p - 1;
while(tp % 2 ==0){
r++;
tp /= 2;
}
int d = tp;
for(int i = 0; i <= k; i++) {
int a = r.nextInt(p - 3) + 2;
int x = powermod(a, d, p);
if(x == 1 || x == p -1) {
continue;
}
boolean con = false;
for(int j = 0; j<= r -1 ;j++) {
x =powermod(x, 2, p)
if(x == 1) {
return false;
}else if(x== p - 1){
con = true;
break;
}
}
if(!con) return false;
return true;
}*/