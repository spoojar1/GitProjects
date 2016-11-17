/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Node{
    TreeNode node;
    int lvl;
    Node(TreeNode node, int lvl){
        this.node = node;
        this.lvl = lvl;
    }
}
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer,List<Integer>> map = new TreeMap<Integer,List<Integer>>();
        BFS(root,map);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        for(int lvl : map.keySet())
            result.add(map.get(lvl));
            
        return result;
    }
    public void BFS(TreeNode root, Map<Integer,List<Integer>> map){
        if(root==null)
            return;
        
        Queue<Node> q = new ArrayDeque<Node>();
        q.add(new Node(root,0));
        
        while(!q.isEmpty()){
            Node n = q.remove();
            
            if(map.get(n.lvl)==null)
                map.put(n.lvl, new ArrayList<Integer>(Arrays.asList(n.node.val)));
            else
                map.get(n.lvl).add(n.node.val);
            
            if(n.node.left!=null)
                q.add(new Node(n.node.left,n.lvl-1));
            if(n.node.right!=null)
                q.add(new Node(n.node.right,n.lvl+1));
        }
    }
}