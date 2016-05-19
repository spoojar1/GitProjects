import java.util.*;
class Graph{
	ArrayList<Integer> list;
	int color;	//1 -> in discovery. 2-> discovered
	Graph(){
		list=new ArrayList<Integer>();
		color=0;
	}
	void add(int v){
		if(!list.contains(v))
			list.add(v);
	}
}
public class ConnectedCellInAGrid {
	static int count;
	static int connectedDFS(Graph g[]){
		int max=-1;
		for(int i=0;i<g.length;i++){
			count=0;
			if(g[i].color==0)
				DFS(g,i);
			if(count>max)
				max=count;
		}
		return max;
	}
	static void DFS(Graph g[],int source){
		count++;
		g[source].color=1;
		Iterator<Integer> k=g[source].list.iterator();
		while(k.hasNext()){
			int child=k.next();
			if(g[child].color==0)
				DFS(g,child);
		}
		g[source].color=2;
	}

    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int m,n;
    	Graph g[];
    	char c[][];
    	m=s.nextInt();
		n=s.nextInt();
		c=new char[m][n];
		g=new Graph[n*m];
		for(int j=0;j<m;j++){
			for(int k=0;k<n;k++){
				c[j][k]=(char)('0'+s.nextInt());
				//System.out.println(c[j][k]);
				g[j*n+k]=new Graph();
				if(c[j][k]=='1'){
					if(j-1>=0 && c[j-1][k]=='1'){
						g[j*n+k].add((j-1)*n+k);
						g[(j-1)*n+k].add(j*n+k);
					}
    				if(k-1>=0 && c[j][k-1]=='1'){
						g[j*n+k].add(j*n+(k-1));
						g[j*n+(k-1)].add(j*n+k);
					}
    				if((k-1>=0 && j-1>=0) && c[j-1][k-1]=='1'){
						g[j*n+k].add((j-1)*n+(k-1));
						g[(j-1)*n+(k-1)].add(j*n+k);
					}
    				if((k+1<n && j-1>=0) && c[j-1][k+1]=='1'){
						g[j*n+k].add((j-1)*n+(k+1));
						g[(j-1)*n+(k+1)].add(j*n+k);
					}
				}
			}
		}
		/*for(int j=0;j<m*n;j++){
			if(!g[j].list.isEmpty()){
				System.out.print(j+" : ");
				Iterator<Integer> k=g[j].list.iterator();
				while(k.hasNext())
					System.out.print(k.next()+",");
				System.out.println();
			}
		}*/
    	System.out.println(connectedDFS(g));
    }
}