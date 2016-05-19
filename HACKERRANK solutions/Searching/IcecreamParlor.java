import java.util.*;
public class IcecreamParlor {
	static void icecreamParlor(int a[],int m){
		int comp;
		for(int i=0;i<a.length-1;i++){
			comp=m-a[i];
			for(int j=i+1;j<a.length;j++){
				if(a[j]==comp){
					System.out.println((i+1)+" "+(j+1));
					return;
				}
			}
		}
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt(),n;
    	int a[],m;
    	for(int i=0;i<t;i++){
    		m=s.nextInt();
    		n=s.nextInt();
    		a=new int[n];
    		for(int j=0;j<n;j++)
    			a[j]=s.nextInt();
    		icecreamParlor(a,m);
    	}
    }
}