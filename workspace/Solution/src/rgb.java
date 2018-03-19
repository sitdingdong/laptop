import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class rgb {
	public static List<String> solution(List<String> a) {
		List<String> ls = new ArrayList<>();
		int[] num = new int[24];
		int r, g, b;
		int dwhite, dred, dgreen, dblue, dblack;
		int[] color =new int[5];
		for(String e : a) {
			for(int i = 0; i < e.length(); i++ ) {
				num[i] = e.charAt(i) - '0';	
			}
			r = num[0] * 128 + num[1] * 64 + num[2] * 32 + num[3] * 16 + num[4] * 8+ num[5] * 4 + num[6] * 2 + num[7];
			g = num[8] * 128 + num[9] * 64 + num[10] * 32 + num[11] * 16 + num[12] * 8+ num[13] * 4 + num[14] * 2 + num[15];
			b = num[16] * 128 + num[17] * 64 + num[18] * 32 + num[19] * 16 + num[20] * 8+ num[21] * 4 + num[22] * 2 + num[23];
			color[0] = (r * r)  + (g * g) + (b * b);
			color[1] = (r -255) * (r - 255) + (g * g) + (b * b);
			color[2] = (r *r) + (g - 255) * (g - 255) + (b * b);
			color[3] = (r * r) + (g * g) + (b - 255) * (b - 255);
			color[4] = (r -255) * (r - 255) + (g - 255) * (g - 255) + (b - 255) * (b - 255);
			Arrays.sort(color);
			if(color[0] == color[1]) {
				ls.add("ambguous");
			}else {
				if(color[0] ==  (r * r)  + (g * g) + (b * b))
					ls.add("white");
				if(color[0] ==  (r -255) * (r - 255) + (g * g) + (b * b))
					ls.add("red");
				if(color[0] ==  (r *r) + (g - 255) * (g - 255) + (b * b))
					ls.add("green");
				if(color[0] ==  (r * r) + (g * g) + (b - 255) * (b - 255))
					ls.add("blue");
				if(color[0] == (r -255) * (r - 255) + (g - 255) * (g - 255) + (b - 255) * (b - 255))
					ls.add("black");
			}
			
		}
		return ls;
		 
	}
	public static void main(String[] args) {
		List<String> rgb = new ArrayList<>();
		rgb.add("000000001111111111111111");
		rgb.add("000000001111111100000000");
		List<String> res = solution(rgb);
		for(String e : res) {
			System.out.println(e);
		}
	}
}
