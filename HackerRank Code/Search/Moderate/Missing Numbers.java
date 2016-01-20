import java.util.*;
import java.util.Map.Entry;
public class Solution{
	static void missingNums(int a[],int b[],int min,int max){
		int count[][]=new int[101][2];
		for(int i=0;i<a.length;i++)
			count[a[i]-min][0]++;
		
		for(int i=0;i<b.length;i++){
			if(count[b[i]-min][1]==0){  //A's count
				count[b[i]-min][0]--;
				if(count[b[i]-min][0]==0)
					count[b[i]-min][1]=1;
			}else
				count[b[i]-min][0]++;
		}
		
		ArrayList<Integer> c=new ArrayList<Integer>();
		for(int i=0;i<count.length;i++){
			if(count[i][0]>0)
				c.add(i+min);
		}

		Collections.sort(c);
		Iterator<Integer> i=c.iterator();
		while(i.hasNext())
			System.out.print(i.next()+" ");
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int min=10001,max=-1;
    	int a[]=new int[n];
    	for(int i=0;i<n;i++)
    		a[i]=s.nextInt();
    	int m=s.nextInt();
    	int b[]=new int[m];
    	for(int i=0;i<m;i++){
    		b[i]=s.nextInt();
    		if(b[i]>max)
    			max=b[i];
    		if(b[i]<min)
    			min=b[i];
    	}
    	//System.out.println(min+" "+max);
    	missingNums(a,b,min,max);
    }
}