import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CoarseGrainedSync implements BSTree{
	final Node snodet3in;
	final Node snodet3ex;
	final Node snodet2in;
	final Node snodet2ex;
	final Node snodet1ex;
	
	//read-write lock so that search operation can be performed simultaneously by multiple threads
	public final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public final Lock r = rwl.readLock();
	public final Lock w = rwl.writeLock();
	
	//defining sentinel nodes with infinite key values for easier traversal of the BST
	CoarseGrainedSync(){
		snodet3in= new Node(999999999,0,1);
		snodet3ex=new Node(999999999,1,1);
		snodet2in= new Node(999999998,0,1);
		snodet2ex= new Node(999999998,1,1);
		snodet1ex= new Node(999999997,1,1);
		
		snodet3in.left=snodet2in;
		snodet3in.right=snodet3ex;
		snodet2in.left=snodet1ex;
		snodet2in.right=snodet2ex;
	}
	
	public boolean search(long key, int thread_id){
		int flag=0;
		Node current;
		
		//coarse read lock on the function
		r.lock();
		try{
			current=snodet3in;
			//traverse the BST to find the key
			while(current!=null){
				if(current.key==key && current.intcheck==1){
					flag=1;
					break;
				}
				else{
					if(key<=current.key)
					 	current=current.left;
					 else if(key>current.key)
						current=current.right;
				}
			}
		}
		finally{
			r.unlock();
		}
		return (flag==1);
	}

	public boolean insert(long key, int thread_id){
		//Define new internal and external nodes
		Node newintNode= new Node(key,0,0);	//key value may change later while inserting
		Node newExNode=new Node(key,1,0);
		//Need to track parent and child nodes in the tree
		Node prev;
		Node current;
		
		int flag=0;
		boolean inschk=false;
		
		//coarse write lock on the function
		w.lock();
		try{
			prev=snodet3in;
			current=snodet2in;
			while(true){
				//Check if the external node is reached and the key does not exist.
				//Also check if prev and current are still part of the tree
				if(current.intcheck==1 && (prev.left==current || prev.right==current) && prev.intcheck==0 && current.key!=key){
					flag=1;
					break;
				}
				else{	
					if(current.intcheck==1 && current.key==key)
						return false;
					if(key<=current.key ){
						prev=current;
						current=current.left;
					}
					else if(key>current.key){
							prev=current;
							current=current.right;
					}
				}
			} //end of while
			
			//If key not found, insert a new node appropriately
			if(flag==1){
				if(prev.left==current){
					if(current.key<key){
						newintNode.key=current.key;	//Change the key of the new internal node
						newintNode.right=newExNode;
						newintNode.left=current;
					}
					else{
						newintNode.left=newExNode;
						newintNode.right=current;
					}
					prev.left=newintNode;
				}
				else if(prev.right==current){
					if(current.key<key){
						newintNode.key=current.key;	//Change the key of the new internal node
						newintNode.right=newExNode;
						newintNode.left=current;
					}
					else{
						newintNode.left=newExNode;
						newintNode.right=current;
					}
					prev.right=newintNode;
				}
				inschk=true;
			}
		}
		finally{
			w.unlock();
		}
		return inschk;
	}
	
	public boolean delete(long key,int thread_id){
		//Need to track grandparent, parent and child nodes in the tree
		Node curr;
		Node parent;
		Node Gparent;
		int flag=0;
		boolean delchk=false;
		
		//coarse write lock on the function
		w.lock();
		try{
			Gparent=snodet3in;
			parent=snodet2in;
			curr=snodet2in.left;
			
			//Search the key in the BST
			while(curr!=null){
				if(curr.key==key && curr.intcheck==1){
					flag=1;
					break;
				}
				else if(key<=curr.key){
					Gparent=parent;
					parent=curr;
					curr=curr.left;
				}
				else if(key>curr.key){
					Gparent=parent;
					parent=curr;
					curr=curr.right;
				}
			}

			//If key found, perform deletion
			if(flag!=0){ 
				if(parent.left==curr)	
				{
					if(Gparent.left==parent)
						Gparent.left=parent.right;
					else
						Gparent.right=parent.right;
				}
				else if(parent.right==curr){
						if(Gparent.left==parent)
							Gparent.left=parent.left;
						else
							Gparent.right=parent.left;
				}
				delchk=true;
			}
		}
		finally{
			w.unlock();
		}		
		return delchk;
	}
	
	//Use in-order traversal for BST validation
	public void traverse(int thread_id){
		inorder(snodet2in.left);
	}
	
	public void inorder(Node node){
		if(node!=null){
			inorder(node.left);
			if(node.intcheck==0){   //Test1 (Check if every left-child<=parent and every right-child>parent)
				if(node.left.key > node.key){
					System.out.println("Invalid BST");
					System.exit(1);
				}
				if(node.right.key <= node.key){
					System.out.println("Invalid BST");
					System.exit(1);
				}
			}else{					//Test2 (Leaf nodes have no left or right child)
				if(node.left!=null || node.right!=null){
					System.out.println("Invalid BST");
					System.exit(1);
				}
			}
			inorder(node.right);
		}
	}
}
