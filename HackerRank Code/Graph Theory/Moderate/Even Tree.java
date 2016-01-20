import java.util.*;

class Node{
	int degree=0;
	int dfscolor,visited;
	ArrayList<Integer> list;
	Node(){
		list=new ArrayList<Integer>();
		degree=0;
		dfscolor=0;	//1-discovery in progress, 2-discovered
		visited=0;	//1-discovery in progress, 2-discovered
	}
	void add(int a){
		list.add(a);
	}
}
public class Solution {
	static Node a[];
	static int breaks=0;
	static void evenTree(){
		degreeDFS(1);
		/*for(int i=1;i<a.length;i++){
			System.out.print(i);
			Iterator<Integer> index=a[i].list.iterator();
			while(index.hasNext())
				System.out.print(" -> "+index.next());
			System.out.println("   degree "+a[i].degree);
		}*/
		calcBreak(1);
	}
	static int degreeDFS(int i){
		a[i].dfscolor=1;
		a[i].degree++;
		Iterator<Integer> index=a[i].list.iterator();
		while(index.hasNext()){
			int tmp=(int) index.next();
			if(a[tmp].dfscolor==0)
				a[i].degree+=degreeDFS(tmp);
		}
		a[i].dfscolor=2;
		//System.out.println(i+" "+a[i].degree);
		return a[i].degree;
	}
	static void calcBreak(int i){
		a[i].visited=1;
		Iterator<Integer> index=a[i].list.iterator();
		while(index.hasNext()){
			int tmp=(int) index.next();
			if(a[tmp].visited==0){
				if(a[tmp].degree%2==0){
					breaks++;
					//System.out.println(breaks+" "+i+" "+tmp);	
				}
				calcBreak(tmp);
			}
		}
		a[i].visited=2;
	}
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		int m=s.nextInt();
		a=new Node[n+1];
		int v1,v2;
		for(int i=0;i<=n;i++)
			a[i]=new Node();
		
		for(int i=0;i<m;i++){
			v1=s.nextInt();
			v2=s.nextInt();
			a[v1].add(v2);
			a[v2].add(v1);
		}
		evenTree();
		System.out.println(breaks);
   }
}
