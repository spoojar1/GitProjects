import java.util.*;
public class IntegerToWords {
	static String digits[]={"one","two","three","four","five","six","seven","eight","nine"};
	static String teens[]={"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
	static String tens[]={"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninty"};
	static String word[]={"","thousand","million"};
	static int count;
	
	static String convertWords(int n){
		String result="";
		if(n<0)
			return "Wrong Input";
		if(n==0)
			return "zero";
		while(n>0){
			if(n%1000!=0)
				result=convertHelp(n%1000)+word[count]+" "+result;
			n=n/1000;
			count++;
		}
		return result;
	}
	
	static String convertHelp(int n){
		String str="";
		int h=n/100;
		if(h!=0)
			str+=digits[h-1]+" hundred ";
		
		n=n%100;
		int t=n/10;
		if(t!=0){
			if(t==1){
				str+=teens[n%10]+" ";
				return str;
			}
			else
				str+=tens[t-2]+" ";
		}
		
		n=n%10;
		if(n!=0)
			str+=digits[n-1]+" ";
		return str;
	}
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		while(true){
			count=0;
			int n=s.nextInt();
			System.out.println(convertWords(n));
		}
   }
}
