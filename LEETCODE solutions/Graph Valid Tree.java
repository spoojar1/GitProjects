public class Solution {
    public boolean validTree(int n, int[][] edges) {
        /*
        LOGIC: Basic Union Find method. Keep a parent array initialized with -1. For every edge, find in what subset each node lies. If they are same, a cycle is detected and hence you exit. You also exit when the number of edges is not n-1 since that is the definition of a tree
        */
        
        if(n-1!=edges.length)
            return false;
            
        int parent[] = new int[n];
        Arrays.fill(parent, -1);
    
        for(int i=0;i<edges.length;i++){
            int p = findSubset(parent,edges[i][0]);
            if(p == findSubset(parent,edges[i][1]))
                return false;
            else
                parent[p] = edges[i][1];
        }
        
        return true;
    }
    
    public int findSubset(int parent[], int i){
        while(parent[i]!=-1)
            i = parent[i];
        
        return i;
    }
}