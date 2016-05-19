import java.util.*;

public class Pairs {
	static int Pairs(int a[],int k){
		int count=0;
		Arrays.sort(a);
		int i=0,j=1;
		while(j<a.length){
			if(a[j]-a[i]>=k){
				if(a[j]-a[i]==k)
					count++;
				i++;
			}else
				j++;
		}
		return count;	
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int k=s.nextInt();
    	int a[]=new int[n];
    	for(int i=0;i<n;i++)
    		a[i]=s.nextInt();
    	System.out.println(Pairs(a,k));
    }
}