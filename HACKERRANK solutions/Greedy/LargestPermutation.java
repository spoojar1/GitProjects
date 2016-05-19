import java.util.*;

public class LargestPermutation {
	static HashMap<Integer,Integer> h=new HashMap<Integer,Integer>();
	static void largePerm(int a[],int k){
		int b[]=Arrays.copyOf(a,a.length);
		Arrays.sort(b);
		int max_index=0;
		for(int i=0;i<a.length && k>0;i++){
			max_index=h.get(b[a.length-1-i]);
			if(max_index!=i){
				h.put(a[i],max_index);
				h.put(a[max_index],i);
				a[i]=a[i]+a[max_index];
				a[max_index]=a[i]-a[max_index];
				a[i]=a[i]-a[max_index];
				k--;
			}
		}
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int k=s.nextInt();
    	int a[]=new int[n];
    	for(int i=0;i<n;i++){
    		a[i]=s.nextInt();
    		h.put(a[i],i);
    	}
    	largePerm(a,k);
    }
}