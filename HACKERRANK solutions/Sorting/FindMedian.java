import java.util.*;

public class FindMedian {
	static int findMedian(int a[],int l,int r,int median){
		if(l<r){
			int index=partition(a,l,r,median);
			if(index==-1)
				return index;
			if(index==median)
				return a[index];
			else{
				int tmp=findMedian(a,l,index-1,median);
				if(tmp!=-1)
					return tmp;
				tmp=findMedian(a,index+1,r,median);
				if(tmp!=-1)
					return tmp;
			}
		}
		return -1;
	}
	
	static int partition(int a[],int l,int r,int median){
		//System.out.println("l "+l+" r "+r+" a[r] "+a[r]);
		if(median>=l && median<=r){
			int j=l-1,tmp;
			for(int i=l;i<r;i++){
				if(a[i]<=a[r]){
					j++;
					tmp=a[j];
					a[j]=a[i];
					a[i]=tmp;
				}
			}
			tmp=a[j+1];
			a[j+1]=a[r];
			a[r]=tmp;
			//System.out.println(j+1);
			//display(a);
			return j+1;
		}
		//System.out.println(-1);
		return -1;
	}
	
	/*static void display(int a[]){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	*/
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int a[]=new int[n];
    	for(int i=0;i<n;i++)
    		a[i]=s.nextInt();
    	int index=findMedian(a,0,n-1,n/2);
    	if(index==-1)
    		System.out.println(a[n/2]);
    	else
    		System.out.println(index);
    }
}