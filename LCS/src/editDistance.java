
public class editDistance {
	public static int editdistance(String a, String b) {
		int[][] lth= new int[a.length() + 1][b.length() + 1];
		int m = a.length();
		int n = b.length();
		for(int i = 0; i <= m; i++) lth[m][0] = i;
		for(int i = 0; i <= n; i++) lth[0][n] = i;
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(a.charAt(i - 1) == b.charAt(j - 1)) lth[i][j] = lth[i -1][j -1];
				else lth[i][j] = Math.min(lth[i - 1][j - 1], Math.min(lth[i - 1][j],lth[i][j - 1])) + 1;
			}
		}
		return lth[m][n];
		
	}

}
