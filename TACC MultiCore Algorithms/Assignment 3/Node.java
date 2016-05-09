import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class Node{
	public long key;				//value of a node
	public Node left,right;			//Left and right child of a node
	public int snodechk,intcheck;	//flags for sentinel and external nodes
	public int marked;				//Keep track of deletion in lazy synchronization
	public final Lock lock=new ReentrantLock(); //Lock object for fine grained lazy synchronization
	
	public Node(){}
	public Node(long key,int intcheck,int snodechk){
		this.key=key;
		right=null;
		left=null;
		this.intcheck=intcheck;
		this.snodechk=snodechk;
		marked=0;
	}
}