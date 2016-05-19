import java.util.*;

class Node{
	int value;
	Node next;
	Node(int data){
		value=data;
	}
}
public class InsertionSort {
	static long count;
	static Node MergeInversions(Node a[],int l,int r){
		int mid;
		if(l!=r){
			mid=(l+r)/2;
			Node left=MergeInversions(a,l,mid);
			Node right=MergeInversions(a,mid+1,r);
			return calInversions(a,left,l,mid,right,mid+1,r);
		}
		return a[l];
	} 
	static Node calInversions(Node a[],Node left,int l1,int r1,Node right,int l2,int r2){
		Node current,first;
		int l2begin=l2;
		if(left.value<=right.value){
			current=first=left;
			left=left.next;
			l1++;
		}else{
			current=first=right;
			right=right.next;
			l2++;
			count+=l2begin-l1;
		}
		while(l1<=r1 && l2<=r2){
			if(left.value<=right.value){
				current=current.next=left;
				left=left.next;
				l1++;
			}else{
				current=current.next=right;
				right=right.next;
				l2++;
				count+=l2begin-l1;
			}
		}
		if(l1>r1)
			current.next=right;
		else
			current.next=left;
		return first;
	}
	
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt(),n;
    	Node a[];
    	for(int j=0;j<t;j++){
    		count=0;
    		n=s.nextInt();
    		a=new Node[n];
    		a[0]=new Node(s.nextInt());
    		for(int i=1;i<n;i++){
    			a[i]=new Node(s.nextInt());
    			a[i-1].next=a[i];
    		}
    		MergeInversions(a,0,n-1);
    		System.out.println(count);
    	}
    }
}