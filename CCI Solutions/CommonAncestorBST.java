class Node{
	Node left,right;
	int data;
	Node(int a){
		data=a;
	}
}
class LL{
	int findCommonParent(Node root,int a,int b){
		if(root==null)
			return 0;
		int tmp=findCommonParent(root.left,a,b)+findCommonParent(root.right,a,b);
		if(tmp==2){
			System.out.println("Common parent is "+root.data);
			System.exit(0);
		}
		if(root.data==a || root.data==b)
			tmp++;
		return tmp;
	}
}
class CommonAncestorBST{
	public static void main(String[] args) {
		//Replace this with code for accepting BST input from console
		Node tmp1=new Node(8);
		Node tmp2=new Node(2);
		Node tmp3=new Node(32);
		tmp1.left=tmp2;
		tmp1.right=tmp3;
		
		Node tmp4=new Node(1);
		Node tmp5=new Node(4);
		tmp2.left=tmp4;
		tmp2.right=tmp5;
		
		Node tmp6=new Node(16);
		Node tmp7=new Node(64);
		tmp3.left=tmp6;
		tmp3.right=tmp7;
		
		Node tmp8=new Node(128);
		tmp7.right=tmp8;
		//Console input code ends here
		
		int tmp=new LL().findCommonParent(tmp1,4,32);
		System.out.println("No common ancestor found");
	}
}
