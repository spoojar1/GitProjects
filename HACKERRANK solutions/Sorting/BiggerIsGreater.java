import java.io.*;
import java.util.*;

public class BiggerIsGreater {
	static void BIG(char c[]){
		char tmp;
		int index=0;
		for(int i=c.length-1;i>0;i--){
			if(c[i]>c[i-1]){
				index=findLexiBig(c,i-1);
				tmp=c[index];
				c[index]=c[i-1];
				c[i-1]=tmp;
				String str=String.valueOf(c);
				char d[]=str.substring(i).toCharArray();
				Arrays.sort(d);
				System.out.println(str.substring(0,i)+String.valueOf(d));
				return;
			}	
		}
		System.out.println("no answer");
	}
	
	static int findLexiBig(char c[],int i){
		int min=100,min_index=-1;
		for(int j=c.length-1;j>i;j--){
			if(c[j]-c[i]>0){
				if((c[j]-c[i])<min){
					min=c[j]-c[i];
					min_index=j;
				}
			}
		}
		return min_index;
	}
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        for(int i=0;i<n;i++)
        	BIG(s.next().toCharArray());
    }
}