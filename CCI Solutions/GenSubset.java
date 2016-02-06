import java.util.*;

public class GenSubset {
	static void genSubsets(String str){
		for(int i=0;i<Math.pow(2,str.length());i++){
			System.out.println(convertToSubset(str,Integer.toBinaryString(i)));
		}
	}
	
	static String convertToSubset(String str1,String str2){
		String tmp="";
		for(int i=str2.length()-1;i>=0;i--){
			if(str2.charAt(i)=='1')
				tmp=str1.charAt(i+str1.length()-str2.length())+tmp;
		}
		return tmp;
	}
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String str=s.next();
		genSubsets(str);
    }
}