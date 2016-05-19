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
public class CountLuck {
	static void countLuckDFS(Graph g[],int whips,int source,int dest,int count){
		if(source==dest){
			if(count==whips)
				System.out.println("Impressed");
			else
				System.out.println("Oops!");
			return;
		}
		g[source].color=1;
		if(g[source].list.size()>1){
			Iterator<Integer> k=g[source].list.iterator();
			int tmp=0;
			while(k.hasNext()){
				int child=k.next();
				if(g[child].color==0)
					tmp++;
			}
			if(tmp>1)
				count++;
		}
		Iterator<Integer> k=g[source].list.iterator();
		while(k.hasNext()){
			int child=k.next();
			if(g[child].color==0)
				countLuckDFS(g,whips,child,dest,count);
		}
		g[source].color=2;
	}

    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt(),n,m,source,dest,whips;
    	Graph g[];
    	char c[][];
    	int count;
    	for(int i=0;i<t;i++){
    		count=0;
    		n=s.nextInt();
    		m=s.nextInt();
    		c=new char[n][m];
    		g=new Graph[n*m];
    		source=-1;
    		dest=-1;
    		for(int j=0;j<n;j++){
    			String str=s.next();
    			for(int k=0;k<m;k++){
    				c[j][k]=str.charAt(k);
    				g[j*m+k]=new Graph();
    				if(c[j][k]!='X'){
    					if(c[j][k]=='*')
    						dest=j*m+k;
    					else if(c[j][k]=='M')
    						source=j*m+k;
    					if(j-1>=0 && c[j-1][k]!='X'){
    						g[j*m+k].add((j-1)*m+k);
    						g[(j-1)*m+k].add(j*m+k);
    					}
	    				if(k-1>=0 && c[j][k-1]!='X'){
							g[j*m+k].add(j*m+(k-1));
							g[j*m+(k-1)].add(j*m+k);
						}
    				}
    			}
    		}
    		whips=s.nextInt();
    		/*for(int j=0;j<m*n;j++){
    			if(!g[j].list.isEmpty()){
    				System.out.print(j+" : ");
    				Iterator<Integer> k=g[j].list.iterator();
    				while(k.hasNext())
    					System.out.print(k.next()+",");
    				System.out.println();
    			}
        	}*/
    		countLuckDFS(g,whips,source,dest,count);
    	}
    }
}