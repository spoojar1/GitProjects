import java.util.Random;

class Instructions{
	int n;
	int search_no,insert_no,delete_no;
	boolean flag=false;

	Instructions(){}
	Instructions(int m){
		n=m;
	}
	
	//Initialize the tree with number of nodes equal to half the key-space
	public void init(int key_space,BSTree tree,int thread_id){
		for(int i=0;i<key_space/2;i++){
			Random r=new Random();
			int key=r.nextInt(key_space)+1;
			tree.insert(key,thread_id);
		}
	}
	
	//Trigger the populate function based on the distribution type selected
	public void prepare(int key_space,int dist_type, BSTree tree, int thread_id){
		switch(dist_type){
			case 1: populate(key_space,90,9,1,tree,thread_id);	//read-dominated
					break;
			case 2: populate(key_space,70,20,10,tree,thread_id);	//mixed
					break;
			case 3: populate(key_space,0,50,50,tree,thread_id);	//write-dominated
					break;
			default:break;
		}
	}
	
	//Perform random search/insert/delete operations on the tree for a particular distribution
	public void populate(int key_space,int search,int insert,int delete, BSTree tree,int thread_id){
		search_no=(search*n)/100;
		insert_no=(insert*n)/100;
		delete_no=(delete*n)/100;
		
		int instr=0;
		for(int i=0;i<n;i++){
			Random r=new Random();	
			while(flag==false && (search_no+insert_no+delete_no)>0){
				instr=r.nextInt(3)+1;
				switch(instr){
					case 1:	search_no=populate_assist(search_no,key_space,instr,tree,thread_id);
							break;
					case 2: insert_no=populate_assist(insert_no,key_space,instr,tree,thread_id);
							break;
					case 3: delete_no=populate_assist(delete_no,key_space,instr,tree,thread_id);
							break;
					default:break;
				}
			}
			flag=false;
		}	
	}
	
	//Randomly generate search/insert/delete operations for a given distribution
	public int populate_assist(int instr_no, int key_space,int instr, BSTree tree,int thread_id){
		if(instr_no>0){
			Random r=new Random();
			int key=r.nextInt(key_space)+1;
			switch(instr){
				case 1:	tree.search(key,thread_id);
						break;
				case 2: tree.insert(key,thread_id);
						break;
				case 3: tree.delete(key,thread_id);
						break;
				default:break;
			}
			flag=true;
			return --instr_no;
		}
		else
			return instr_no;
	}
}