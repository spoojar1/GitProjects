import java.util.*;
class Node{
	Node left,right;
	int data;
	Node(int data){
		this.data=data;
		this.left=null;
		this.right=null;
	}
	void setNode(Node left,Node right){
		this.left=left;
		this.right=right;
	}
}
class NodePair{
	Node head, tail;
	NodePair(Node head,Node tail){
		this.head=head;
		this.tail=tail;
	}
}
public class BSTDoubleLL {
	static NodePair convertLL(Node root){
		if(root==null)
			return null;
		NodePair n1=convertLL(root.left);
		NodePair n2=convertLL(root.right);
		
		if(n1!=null)
			concat(n1.tail,root);
		if(n2!=null)
			concat(root,n2.tail);
		
		return new NodePair(n1==null?root:n1.head,n2==null?root:n2.tail);
	}
	static void concat(Node x,Node y){
		x.right=y;
		y.left=x;
	}
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		//Replace this with code for accepting BST input from console
		Node root=new Node(4);
		Node node1=new Node(2);
		Node node2=new Node(5);
		Node node11=new Node(1);
		Node node12=new Node(3);
		Node node22=new Node(6);
		Node node111=new Node(0);
		root.setNode(node1,node2);
		node1.setNode(node11,node12);
		node2.setNode(null,node22);
		node11.setNode(node111,null);
		//Input code ends here
		
		NodePair node=convertLL(root);
		Node tmp=node.head;
		
		while(tmp!=null){
			System.out.println(tmp.data);
			tmp=tmp.right;
		}
   }
}
