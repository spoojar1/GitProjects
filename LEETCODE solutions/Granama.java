import java.util.Arrays;
import java.util.Scanner;

class Granama{
	public static boolean checkMeGranama(String str1, String str2){
		int c1[]=new int[26], c2[]=new int[26];;
		for(int i=0;i<str1.length();i++)
			c1[str1.charAt(i)-'a']++;
		
		for(int i=0;i<str2.length();i++)
			c2[str2.charAt(i)-'a']++;
		
		return Arrays.equals(c1, c2);
	}
	
	public static boolean checkChefGranama(String str1, String str2){
		boolean c1[]=new boolean[26], c2[]=new boolean[26];;
		for(int i=0;i<str1.length();i++)
			c1[str1.charAt(i)-'a'] = true;
		
		for(int i=0;i<str2.length();i++)
			c2[str2.charAt(i)-'a'] = true;
		
		return Arrays.equals(c1, c2);
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int t=s.nextInt();
		s.nextLine(); //dummy since nextInt does not read carriage return
		for(int i=0;i<t;i++){
			String str[] = s.nextLine().split(" ");
			System.out.println(checkMeGranama(str[0],str[1])==checkChefGranama(str[0],str[1])?"YES":"NO");
		}
	}
}