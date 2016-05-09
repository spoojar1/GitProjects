import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

class Node{
	Node left,right;
	int data;
	Node(int a){
		data=a;
	}
}
class LL{
	ArrayList<LinkedList<Node>> CL(Node root){
		ArrayList<LinkedList<Node>> result=new ArrayList<LinkedList<Node>>();
		LinkedList<Node> current=new LinkedList<Node>();
		if(root!=null)
			current.add(root);
		while(current.size()>0){
			LinkedList<Node> parents=current;
			result.add(current);
			current=new LinkedList<Node>();
			for(Node parent: parents){
				if(parent.left!=null)
					current.add(parent.left);
				if(parent.right!=null)
					current.add(parent.right);
			}
		}
		return result;
	}
}
class BST2LL{
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
		
		ArrayList<LinkedList<Node>> a=new LL().CL(tmp1);
		int n=0;
		for(LinkedList<Node> l:a){
			ListIterator<Node> ll=l.listIterator();
			System.out.println("Printing level "+n++);
			while(ll.hasNext())
				System.out.println(ll.next().data+" -> ");
		}
	}
}
