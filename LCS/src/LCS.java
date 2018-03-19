
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LCS {
	public static String loadFile(String name) {
		Scanner s = null;
		String a;
		try {
			s = new Scanner(new FileReader(name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		s.useDelimiter("\\Z");
		a = s.next();
		s.close();
		return a;
	}
	public static int lcs(String a, String b) {
		int m = a.length();
		int n = b.length();
		int[][] lth = new int[m + 1][n + 1];
		for (int i = 1; i <= m ; i ++) {
			for (int j = 1; j <= n ; j ++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) lth[i][j] = lth[i - 1][j - 1] + 1;
				else lth[i][j] = Math.max(lth[i][j - 1], lth[i - 1][j]);
		}
	}
	return lth[m][n];
	}
	public static void main(String[] args) {
		String a = loadFile(args[0]);
		String b = loadFile(args[1]);
		System.out.println(lcs(a, b)); 
	}
}
