import java.util.*;

public class SherlockNWatson {

    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int k=s.nextInt();
    	int q=s.nextInt();
    	int a[]=new int[n];
    	int b[]=new int[n];
    	for(int i=0;i<n;i++){
    		a[i]=s.nextInt();
    		b[(i+k)%n]=a[i];
    	}
    	for(int i=0;i<q;i++)
    		System.out.println(b[s.nextInt()]);
    }
}