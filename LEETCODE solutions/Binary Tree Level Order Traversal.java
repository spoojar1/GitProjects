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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Node> q = new ArrayDeque<Node>();
        
        if(root==null)
            return result;
            
        int count = 0;
        q.add(new Node(root,0));
        
        List<Integer> list = new ArrayList<Integer>();
        
        while(!q.isEmpty()){
            Node n = q.poll();
            if(n.lvl!=count){
                count = n.lvl;
                result.add(list);
                list = new ArrayList<Integer>();
            }
            
            list.add(n.node.val);
            
            if(n.node.left!=null)
                q.add(new Node(n.node.left,n.lvl+1));
                
            if(n.node.right!=null)  
                q.add(new Node(n.node.right,n.lvl+1));
            
        }
        
        result.add(list);
        return result;
    }
}