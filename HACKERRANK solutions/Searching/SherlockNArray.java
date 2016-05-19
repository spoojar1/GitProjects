import java.util.*;
class Node{
	int data,leftsum,rightsum;
	Node(int data,int leftsum){
		this.data=data;
		this.leftsum=leftsum;
	}
}
public class SherlockNArray {
	static String sherlockArray(Node a[]){
		int n=a.length;
		a[n-1].rightsum=0;
		for(int i=n-2;i>=0;i--){
			a[i].rightsum=a[i+1].data+a[i+1].rightsum;
			if(a[i].rightsum==a[i].leftsum)
				return "YES";
		}
		return "NO";
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt(),n;
    	Node a[];
    	for(int i=0;i<t;i++){
    		n=s.nextInt();
    		a=new Node[n];
    		a[0]=new Node(s.nextInt(),0);
    		for(int j=1;j<n;j++)
    			a[j]=new Node(s.nextInt(),a[j-1].data+a[j-1].leftsum);
    		if(n==1)
    			System.out.println("YES");
    		else
    			System.out.println(sherlockArray(a));
    	}
    }
}