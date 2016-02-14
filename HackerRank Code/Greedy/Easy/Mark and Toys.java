import java.util.*;
public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int amount=s.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++)
        	a[i]=s.nextInt();
        System.out.println(MarkToys(a,amount));
    }
    static int MarkToys(int a[],int amount){
    	int count=0;
    	Arrays.sort(a);
    	for(int i=0;i<a.length;i++){
    		//System.out.println(a[i]+" "+count+" "+amount);
    		if(a[i]>amount)
    			break;
    		else{
    			count++;
    			amount-=a[i];
    		}
    	}
    	return count;
    }
}