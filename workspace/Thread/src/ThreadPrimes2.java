

import java.util.*;

public class ThreadPrimes2{
	List<Integer> ls;
	int total;
	int numsCPU;
	class MyThreadPrimes extends Thread {
		private int start;
		private int end;
		public MyThreadPrimes(int s, int e) {
			start = s;
			end = e;
			ls = new ArrayList<>();
		}
		@Override
		public void run() {
			for(int i = start; i<=end; i++) {
			    int j ;
                j = 2;
                while (i % j != 0) {
                    j++; 
                }
                if (j == i) {
                    System.out.println(i);
                }
				
			}
		};
//		
//		public synchronized void putNum(int n) {
//			ls.add(n);
//		}
	}
	public ThreadPrimes2(int t, int c) {
		total = t;
		numsCPU = c;
		int s = 0;
		int e = total / numsCPU;
		ls = new ArrayList<>(1000);
		for(int i = 0; i < numsCPU; i++) {
			Thread t1 = new MyThreadPrimes(s, e);
			t1.start();
			s = e + 1;
			e += total / numsCPU;
		}
	}
	public void size() {
		System.out.println(ls.size() + "FFFFFFFFFFFFFFF");
	}
	public static void main(String[] arg) {
		ThreadPrimes2 t = new ThreadPrimes2(1000, 2);
		//t.size();
	}
}

