class Node{
    int occur, num;
    Node(int occur, int num) { 
        this.occur = occur;
        this.num = num;
    }
}

class PriorityQueueComparator implements Comparator<Node>{
	public int compare(Node n1, Node n2) {
        return n1.occur > n2.occur ? 1 : -1;
    }
}

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        PriorityQueue<Node> q = new PriorityQueue<Node>(k+1, new PriorityQueueComparator());
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        for(int num: nums){
            if(map.containsKey(num))
                map.put(num,map.get(num)+1);
            else
                map.put(num,1);
        }
        
        for(int key : map.keySet()){
            q.add(new Node(map.get(key),key));
            if(q.size()==k+1)
                q.poll();
        }
        
        Node n;
        List<Integer> list = new ArrayList<Integer>();
        while((n = q.poll()) != null)
            list.add(n.num);
            
        return list;
    }
}