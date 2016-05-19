import java.util.*;
public class Pangrams {
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	char c[]=s.nextLine().toCharArray();
    	int a[]=new int[26];
    	for(int i=0;i<c.length;i++){
    		if(c[i]>=65 && c[i]<=90)
    			a[c[i]-'A']++;
    		else if(c[i]>=97 && c[i]<=122)
    			a[c[i]-'a']++;
    	}
    	for(int i=0;i<a.length;i++){
    		if(a[i]==0){
    			System.out.println("not pangram");
    			return;
    		}	
    	}
    	System.out.println("pangram");
    }
}