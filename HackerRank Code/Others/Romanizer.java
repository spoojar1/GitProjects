/*Convert Number to Roman Literals
Sample Input
5
30
55
90
120
900
Sample Output
XXX
LV
XC
CXX
CM
*/

import java.util.*;

public class Solution {
	static String[] romanizer(int[] num) {
		int n=num.length;
		String res[]=new String[n];
		for(int i=0;i<n;i++)
			res[i]=helpRoman(num[i]);
		return res;
	}

	static String helpRoman(int num) {
		String str="";
		String hunds[]={"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
		String tens[]={"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
		String units[]={"","I","II","III","IV","V","VI","VII","VIII","IX"};
		
		while(num>=1000){
			str+='M';
			num-=1000;
		}
		str=str+hunds[num/100];
		num=num%100;
		str=str+tens[num/10];
		num=num%10;
		str=str+units[num];
		return str;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] res;

		int _A_size = Integer.parseInt(in.nextLine());
		int[] _A = new int[_A_size];
		int _A_item;
		for (int _A_i = 0; _A_i < _A_size; _A_i++) {
			_A_item = Integer.parseInt(in.nextLine());
			_A[_A_i] = _A_item;
		}

		res = romanizer(_A);
		for (int res_i = 0; res_i < res.length; res_i++) {
			System.out.println(res[res_i]);
		}
	}
}