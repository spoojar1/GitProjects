import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		StringTokenizer tokens=new StringTokenizer(str," !,?._'@");
		System.out.println(tokens.countTokens());
		while(tokens.hasMoreTokens())
			System.out.println(tokens.nextToken());
	}
}
