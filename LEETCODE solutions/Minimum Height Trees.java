public class Solution {
    /*BRUTE FORCE: apply BFS for each node and calculate the shortest height trees
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n<2)
            return new ArrayList<Integer>(Arrays.asList(0));
            
        Map<Integer, List<Integer>> adj_list = create_adj_list(edges);
        Map<Integer, List<Integer>> mins = new HashMap<Integer, List<Integer>>();

        int min = Integer.MAX_VALUE;
        for(int key : adj_list.keySet()){
            int h = findHeight(key, n, adj_list);
            if(mins.containsKey(h)){
                List<Integer> list =  mins.get(h);
                list.add(key);
                mins.put(h, list);
            }else
                mins.put(h, new ArrayList<Integer>(Arrays.asList(key)));
            
            min = Math.min(min, h);
        }
        
        return mins.get(min);
        
    }
    
    public Map<Integer, List<Integer>> create_adj_list(int[][] edges){
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    
        for(int i=0;i<edges.length;i++){
            if(map.containsKey(edges[i][0])){
                List<Integer> list =  map.get(edges[i][0]);
                list.add(edges[i][1]);
                map.put(edges[i][0], list);
            }else
                map.put(edges[i][0], new ArrayList<Integer>(Arrays.asList(edges[i][1])));    
            
            if(map.containsKey(edges[i][1])){
                List<Integer> list =  map.get(edges[i][1]);
                list.add(edges[i][0]);
                map.put(edges[i][1], list);
            }else
                map.put(edges[i][1], new ArrayList<Integer>(Arrays.asList(edges[i][0]))); 
        }
        
        return map;
    }
    
    public int findHeight(int root, int n, Map<Integer, List<Integer>> adj_list){
        
        int visited[] = new int[n];
        Queue<Integer> q1 = new ArrayDeque<Integer>();
        Queue<Integer> q2 = new ArrayDeque<Integer>();
        int height = 0;
        q1.add(root);

        while(true){
            while(!q1.isEmpty()){
                int node = q1.remove();
                visited[node] = 1;
                
                for(int child : adj_list.get(node)){
                    if(visited[child]!=1)
                        q2.add(child);
                }
            }
            
            if(q2.isEmpty())
                return height;
                    
            q1 = q2;
            height++; 
            q2 = new ArrayDeque<Integer>();
        }
    } */
    
    /*LOGIC:
    keep on removing leaf nodes until 2 or less nodes are remaining. Cannot change original adj_list since, if all the nodes of a node are leaves, it wont be left with anything and hence we cannot use .size()==1 for tracking that. Hence, we need a separate list to keep track of the leaves
    */
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n==1)
            return new ArrayList<Integer>(Arrays.asList(0));
            
        List<List<Integer>> adj_list = createAdj_List(n, edges);
        
        List<Integer> res = new ArrayList<Integer>();
        List<Integer> leaves = new ArrayList<Integer>();

        for(int i=0;i<adj_list.size();i++)
            if(adj_list.get(i).size()==1)
                leaves.add(i);
                
        while(n>2){
            
            n -= leaves.size();
            List<Integer> new_leaves = new ArrayList<Integer>();
            
            for(int i: leaves){
                int node = adj_list.get(i).get(0);
                
                adj_list.get(node).remove((Integer)i);
                
                if(adj_list.get(node).size()==1)
                    new_leaves.add(node);
            }
            
            leaves = new_leaves;
        }
        
        for(int i: leaves)
            res.add(i);
        
        return res;        
    }
    
    public List<List<Integer>> createAdj_List(int n, int[][] edges){
        List<List<Integer>> adj_list = new ArrayList<List<Integer>>(n);
        
        for(int i=0;i<n;i++)
            adj_list.add(new ArrayList<Integer>());
        
        for(int i=0;i<edges.length;i++){
            adj_list.get(edges[i][0]).add(edges[i][1]);
            adj_list.get(edges[i][1]).add(edges[i][0]);
        }
        
        return adj_list;
    }
}