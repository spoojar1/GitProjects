import java.util.*;
public class LonelyInteger {
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int a[]=new int[100];
    	for(int i=0;i<n;i++)
    		a[s.nextInt()]++;
    	for(int i=0;i<a.length;i++){
    		if(a[i]==1){
    			System.out.println(i);
    			return;
    		}	
    	}
    }
}