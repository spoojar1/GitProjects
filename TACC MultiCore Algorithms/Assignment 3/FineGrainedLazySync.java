class FineGrainedLazySync implements BSTree{
	final Node snodet3in;
	final Node snodet3ex;
	final Node snodet2in;
	final Node snodet2ex;
	final Node snodet1ex;
	
	//defining sentinel nodes with infinite key values for easier traversal of the BST
	FineGrainedLazySync(){
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
		//no need to lock search operation in Lazy synchronization
		int flag=0;
		Node current;
		current=snodet3in;
		//traverse the BST to find the key
		while(current!=null){
			if(current.key==key && current.intcheck==1 && current.marked==0){
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
		return(flag==1);	
	}
	
	public boolean insert(long key, int thread_id){
		//Define new internal and external nodes
		Node newintNode= new Node(key,0,0);	//key value may change later while inserting
		Node newExNode=new Node(key,1,0);
		//Need to track parent and child nodes in the tree
		Node prev;
		Node current;
		
		boolean insertchk=false;
		int flag;

		while(true){
			flag=0;
			prev=snodet3in;
			current=snodet2in;
			while(current!=null){
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
			}//end of while
			
			//If key not found, lock the prev and current nodes, and validate if they are still part of the tree
			if(flag==1){
				prev.lock.lock();
				try{
					current.lock.lock();
					try{
						if(validate(prev, current)){
							//If validation succeeds, insert a new node appropriately
							if(prev.left==current){
								if(current.key<key){
									newintNode.key=current.key;
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
									newintNode.key=current.key;
									newintNode.right=newExNode;
									newintNode.left=current;
								}
								else{
									newintNode.left=newExNode;
									newintNode.right=current;
								}
								prev.right=newintNode;
							}
							insertchk=true;
							break;
						}
					}//innerTry
					finally{
						current.lock.unlock();
					}
				}//outerTry
				finally{
					prev.lock.unlock();
				}
			}	
		}//outer while
		return insertchk;
	}//end of insert

	public boolean delete(long key,int thread_id){
		//Need to track grandparent, parent and child nodes in the tree
		Node curr;
		Node parent;
		Node Gparent;
		int flag;
		boolean delchk=false;

		while(true){
			flag=0;
			Gparent=snodet3in;
			parent=snodet2in;
			curr=snodet2in.left;

			//Search the key in the BST
			while(curr!=null){
				if(curr.key==key && curr.intcheck==1){
					flag=1;
					break;
				}
				else {
					if(key<=curr.key){
						Gparent=parent;
						parent=curr;
						curr=curr.left;
					}
					else {
						if(key>curr.key){
						Gparent=parent;
						parent=curr;
						curr=curr.right;
						}
					}
				}
			}
			
			//If key is found, lock the grandparent, parent and current nodes, and validate if they are still part of the tree
			if(flag==1){
				Gparent.lock.lock();
				try{
					parent.lock.lock();
					try{
						curr.lock.lock();
						try{	
							if(validate(Gparent, parent, curr)){
								//If validation succeeds, perform deletion
								parent.marked=curr.marked=1;
								if(parent.left==curr){
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
								break;
							}
						}finally{
							curr.lock.unlock();
						}
					}finally{
						parent.lock.unlock();
					}
				}finally{
					Gparent.lock.unlock();
				}	
			}
			else break;
		}//outer while()
		return delchk;
	}
	
	//Use in-order traversal for BST validation
	public void traverse(int thread_id){
		inorder(snodet2in.left);
	}
	
	public void inorder(Node node){
		if(node!=null){
			inorder(node.left);
			if(node.intcheck==0){   //Test1 (Check if every left child<=parent and every right child>parent)
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
	
	//Validate if parent and current nodes are still part of the tree
	public boolean validate(Node prev, Node curr){
		return(prev.marked==0 && curr.marked==0 && (prev.left==curr || prev.right==curr));
	}
	
	//Validate if grandparent, parent and current nodes are still part of the tree
	public boolean validate(Node Gparent,Node parent, Node curr){
		return(Gparent.marked==0 && parent.marked==0 && curr.marked==0 && (Gparent.left==parent || Gparent.right==parent) && (parent.left==curr || parent.right==curr));
	}
}