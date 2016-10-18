/*
Logic: Find longest sequence of palindrome within the string. Remaining number of characters is your answer.
eg: for SARGAM, longest sequence of palindrome is ARA. Hence, the answer is 6-3=3 

To find longest sequence of palindrome, find the longest common subsequence(LCS) between the given string
and its reverse.
Time complexity : O(n^2) (same as LCS)
Space complexity: O(n^2) (same as LCS) but we don't need O(n^2) space, we can do with O(2*n) ~ O(n) space as shown below.
*/

import java.util.*;
public class Palindrome_LCS{
	public static int calc_missing_palin(String str){
		int lcs[][] = new int[2][str.length()+1]; // 2*n instead of n^2;
		
		//traverse from end of the string which basically means we are comparing the string with its reverse
		for(int i=str.length()-1;i>=0;i--){
			for(int j=0;j<str.length();j++){
				if(str.charAt(i)==str.charAt(j))
					lcs[1][j+1] = lcs[0][j] + 1;
				else
					lcs[1][j+1] = Math.max(lcs[1][j],lcs[0][j+1]);
			}
			
			//re-using 2nd row as 1st for next iteration. This step allows us to bring space complexity from O(n^2) to O(n)
			for(int j=0;j<=str.length();j++)
				lcs[0][j] = lcs[1][j];
		}
		//lcs[1][str.length()] contains the length of the LCS
		return str.length() - lcs[1][str.length()];
		
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int t=s.nextInt();
		for(int i=0;i<t;i++){
			System.out.println(calc_missing_palin(s.next()));
		}
	}
}