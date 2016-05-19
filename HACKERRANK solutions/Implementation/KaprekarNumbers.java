import java.io.*;
import java.util.*;

public class KaprekarNumbers {
	static boolean kaprekar(long i){
		int a=0,b,tmp1;
		String str1=Long.toString(i);
		String str2=Long.toString(i*i);
		tmp1=str2.length()-str1.length();
		if(str1.length()==1){
			if(str1.length()!=str2.length()){
				a=Integer.parseInt(str2.substring(0,1));
				b=Integer.parseInt(str2.substring(1));
			}
			else	
				b=Integer.parseInt(str2);
		}
		else{
			a=Integer.parseInt(str2.substring(0,tmp1));
			b=Integer.parseInt(str2.substring(tmp1));
			/*while(a%10==0){
				a=a/10;
			}*/
		}

		if(i==(a+b))
			return true;
		else
			return false;
	}
	
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int p=s.nextInt();
    	int q=s.nextInt();
    	//System.out.println(Long.toString(4294967296l));
    	boolean flag=false;
    	for(int i=p;i<=q;i++){
    		//System.out.println(i);
    		if(kaprekar(i)){
    			System.out.print(i+" ");
    			flag=true;
    		}
    	}
    	if(!flag)
    		System.out.println("INVALID RANGE");
    }
}