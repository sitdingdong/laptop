import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KpairSum {
	public static int solution(int[] a, int target) {
		int x = 0;
		Arrays.sort(a);
		 /*for(int i=0;i<a.length-1;i++){   
             for(int j=i+1;j<a.length;j++){   
                 if (a[i]>a[j]){   
                     int temp=a[i];   
                     a[i]=a[j];   
                     a[j]=temp;   
                 }*/
		int b = 0;
		//for (int i =0; i < a.length; i++){
			int front = 0;
			int back = a.length - 1;
			while(front < back){
				if(a[front] + a[back] == target) {
					b += 1;
				    front++;
				    back--;
				    while(front<back && a[front] == a[front-1]) front++;
	                while(front<back && a[front] == a[back+1]) back--;
				    /*if(a[front] == a[front-1])
				    	front++;
				    if(a[back] == a[back])
				    	back--;*/
				}
				else if(a[front] + a[back] >target)
	                back--;
	            else
	                front++;
				
			}
			
		//}
		return b;
	}
	public static int solution2(int[] a, int target) {
		/*int b = 0;
		int c = 0;
		
		for(int i = 0; i < a.length-1; i++) {
			c = i+1;
			
			if(a[i] + a[c] == target){
				b += 1;
				c += 1;
			}
			
		}
		return b;*/
		int b = 0;
		Map<Integer, Integer> map = new HashMap<>();  
		
		for (int i = 0; i < a.length; i++){
		    if (map.containsKey(target - a[i])) {  
		      b += map.get(target - a[i]);
		    }
		    //map.put(a[i], map.getOrDefault(a[i], 0) + 1);
		    if(map.containsKey(a[i])) {
		    	int val = map.get(a[i]) + 1;
		    	map.put(a[i], val);
		    	
		    } else {
		    	map.put(a[i], 1);
		    }
		}
		   
		return b;
		    
		
	}
	
	public static void main(String[] arg) {
		int[] a = new int[]{2,2,2,2,1};
		System.out.println(solution2(a, 4));
	}
}
