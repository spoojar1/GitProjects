class Node{
	Node left,right;
	int data;
	Node(int a){
		data=a;
	}
}
class MBST{
	Node cMBST(int a[],int start,int end){
		if(start>end)
			return null;
		int mid=(start+end)/2;
		Node n=new Node(a[mid]);
		n.left=cMBST(a,start,mid-1);
		n.right=cMBST(a,mid+1,end);
		if(n.left!=null)
			System.out.println(n.left.data+" left of "+n.data);
		if(n.right!=null)
			System.out.println(n.right.data+" right of "+n.data);
		return n;
	}
}
class PrepareBST{
	public static void main(String[] args) {
		int a[]={1,2,4,8,16,32,64,128};		//Replace this with code for accepting input from console
		MBST m=new MBST();
		Node n=m.cMBST(a,0,a.length-1);
	}
}
