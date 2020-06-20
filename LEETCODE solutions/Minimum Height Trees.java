class Solution {
    Map<Integer, List<Integer>> adjList;
        
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(edges.length == 0)
            return new ArrayList<Integer>(Arrays.asList(0));
        
        adjList = new HashMap<>();
        preProcess(edges);
        
        List<Integer> leaves = new ArrayList<>();
        for(int key: adjList.keySet()){
            if(adjList.get(key).size()==1)
                leaves.add(key);
        }
        
        while(n>2){
            n-=leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for(int leaf: leaves){
                int neighbour = adjList.get(leaf).get(0);
                adjList.get(neighbour).remove(new Integer(leaf));
                
                if(adjList.get(neighbour).size() ==1)
                    newLeaves.add(neighbour);
            }
            
            leaves = newLeaves;
        }
        
        return leaves;
    }
    
    private void preProcess(int[][] edges){
        for(int i=0; i<edges.length; i++){
            //store neighbours for each node
            List<Integer> neighbours = adjList.getOrDefault(edges[i][0], new ArrayList<Integer>());
            neighbours.add(edges[i][1]);
            adjList.put(edges[i][0], neighbours);
            
            neighbours = adjList.getOrDefault(edges[i][1], new ArrayList<Integer>());
            neighbours.add(edges[i][0]);
            adjList.put(edges[i][1], neighbours);
        }
    }
}