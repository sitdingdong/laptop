package String;
public class StringEqual {
	public static void main(String[] arg) {
		String s1 = "abc";
		String s2 = "abc";
		String s3 = "ab";
		String s4 = "ab" + "c";
		s3 += 'c';
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s3));
		System.out.println(s1 == s4);
	}
}
