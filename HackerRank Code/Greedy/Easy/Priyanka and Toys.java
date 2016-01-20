import java.util.*;

public class Solution {
	static int pToys(int a[]){
		Arrays.sort(a);
		int count=1,weight=a[0];
		for(int i=1;i<a.length;i++){
			if(a[i]>weight+4){
				weight=a[i];
				count++;
			}
		}
		return count;
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int a[]=new int[n];
    	for(int i=0;i<n;i++)
    		a[i]=s.nextInt();
    	System.out.println(pToys(a));
    }
}